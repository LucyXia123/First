package com.tsinghuadtv.www.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QrcodeUtils {
	
	public static void createQRCode(String qrCodeData, String charset, 
			Map<EncodeHintType, Object> hintMap, int qrCodeheight, int qrCodewidth, OutputStream stream) 
					throws WriterException,	IOException {
		
		MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
		
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset), 
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		
		MatrixToImageWriter.writeToStream(matrix, "jpg", stream, config);
		
	}
	
    public static Result readQRCode(String filePath, String charset,
                             Map<DecodeHintType, ?> hintMap, int tag){
        Result qrCodeResult = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(filePath);
            BufferedImage bi =  ImageIO.read(is);
            LuminanceSource ls = new BufferedImageLuminanceSource(bi);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(ls));
            qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                    hintMap);
            if(null != is) is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	finally{
            try {
                if(null != is) is.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        //
        return qrCodeResult;
    }
}
