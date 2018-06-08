/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsinghuadtv.www.util;

import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author liwenbo
 */
public class WebAppException extends HttpStatusCodeException
{

    public WebAppException(HttpStatus statusCode)
    {
        super(statusCode);
    }

    public WebAppException(HttpStatus statusCode, String statusText)
    {
        super(statusCode, statusText);
    }

    public WebAppException(HttpStatus statusCode, String statusText,
            byte[] responseBody, Charset responseCharset)
    {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public WebAppException(HttpStatus statusCode, String statusText,
            HttpHeaders responseHeaders, byte[] responseBody,
            Charset responseCharset)
    {
        super(statusCode, statusText, responseHeaders, responseBody,
                responseCharset);
    }
    
    
    
}
