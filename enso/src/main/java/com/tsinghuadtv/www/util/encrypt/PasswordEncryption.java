package com.tsinghuadtv.www.util.encrypt;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryption {

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	public static final int SALT_BYTE_SIZE = 32 / 2;

	public static final int HASH_BIT_SIZE = 128 * 4;

	public static final int PBKDF2_ITERATIONS = 1000;

	public static boolean authenticate(String attemptedPassword, String encrytedPassword, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
		return encryptedAttemptedPassword.equals(encrytedPassword);
	}

	public static String getEncryptedPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), fromKex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return toHex(factory.generateSecret(spec).getEncoded());
	}

	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return toHex(salt);
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

	private static byte[] fromKex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	public static void main(String[] args) {
		String password = "test";
		String salt;
		String ciphertext;
		try {

			salt = PasswordEncryption.generateSalt();
			ciphertext = PasswordEncryption.getEncryptedPassword(password, salt);
			boolean result = PasswordEncryption.authenticate(password, ciphertext, salt);

			System.out.println(password + "  " + password.length());
			System.out.println(salt + "  " + salt.length());
			System.out.println(ciphertext + "  " + ciphertext.length());
			if (result) {
				System.out.println("succeed");
			} else {
				System.out.println("failed");
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException");
		} catch (InvalidKeySpecException e) {
			System.out.println("InvalidKeySpecException");
		}

	}

}
