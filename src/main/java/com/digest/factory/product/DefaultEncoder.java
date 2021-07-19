package com.digest.factory.product;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DefaultEncoder implements IEncoder {

    private final MessageDigest messageDigest;

    private final String algorithm;

    private final String charsetName;

    public DefaultEncoder(String algorithm, String charsetName) throws NoSuchAlgorithmException {
        String _algorithm = (algorithm == null || "".equals(algorithm)) ? "SHA-1" : algorithm;
        String _charsetName = (charsetName == null || "".equals(charsetName)) ? "UTF-8" : charsetName;
        this.messageDigest = MessageDigest.getInstance(_algorithm);
        this.algorithm = _algorithm;
        this.charsetName = _charsetName;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override
    public String encode(String userName, String plaintextPwd) throws UnsupportedEncodingException {
        byte[] value;
        byte[] passwordBytes;
        byte[] usernameBytes;
        String passwordValue;
        synchronized (this.messageDigest) {
            this.messageDigest.reset();
            passwordBytes = plaintextPwd.getBytes(this.charsetName);
            usernameBytes = userName.getBytes(this.charsetName);
            value = this.messageDigest.digest(passwordBytes);
            this.messageDigest.update(usernameBytes);
            value = this.messageDigest.digest(value);
            passwordValue = new String(Base64.encodeBase64(value), this.charsetName);
        }
        return passwordValue;
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getCharsetName() {
        return this.charsetName;
    }
}
