package com.digest.factory.product;

import com.digest.factory.digest.SM3Digest;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class SM3Encoder implements IEncoder{

    private final SM3Digest sm3Digest;

    private final String algorithm;

    private final String charsetName;
    
    public SM3Encoder(String algorithm ,String charsetName) {
        this.charsetName = (charsetName == null || "".equals(charsetName)) ? "UTF-8" : charsetName;
        this.sm3Digest = new SM3Digest();
        this.algorithm = "SM3".equals(algorithm) ? algorithm: "SM3";
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override
    public String encode(String userName, String plaintextPwd) throws UnsupportedEncodingException {
        byte[] value;
        String passwordValue;
        value = new byte[32];
        byte[] bytes;
        try {
            bytes = (userName + plaintextPwd).getBytes(this.charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException(e);
        }
        synchronized (sm3Digest){
            sm3Digest.update(bytes, 0, bytes.length);
            sm3Digest.doFinal(value, 0);
        }
        passwordValue =  getHeader() + new String(Base64.encodeBase64(value), charsetName);
        return passwordValue;
    }

    @Override
    public String getHeader() {
        return  "$" + this.algorithm + "$"  ;
    }

    @Override
    public String getCharsetName() {
        return this.charsetName;
    }

}
