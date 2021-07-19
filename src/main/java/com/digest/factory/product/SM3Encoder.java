package com.digest.factory.product;

import com.digest.factory.digest.SM3Digest;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class SM3Encoder implements IEncoder{

    private final SM3Digest sm3Digest;

    private final String algorithm;

    private final String charsetName;
    
    public SM3Encoder(String algorithm ,String charsetName) {
        // 默认 UTF-8
        this.charsetName = (charsetName == null || "".equals(charsetName)) ? "UTF-8" : charsetName;
        this.sm3Digest = new SM3Digest();
        // SM3 是固定的
        this.algorithm = "SM3".equals(algorithm) ? algorithm: "SM3";
    }

    /**
     * 获取当前加密算法
     * @return 当前加密算法
     */
    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }

    /**
     * 加密方法
     * @param userName 用户名
     * @param plaintextPwd 密码明文
     * @return 加密后的密码密文
     * @throws UnsupportedEncodingException
     */
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

    /**
     * 获取加密头
     * @return 返回加密头
     */
    @Override
    public String getHeader() {
        return  "$" + this.algorithm + "$"  ;
    }

    /**
     * 获取字符集
     * @return 当前字符集
     */
    @Override
    public String getCharsetName() {
        return this.charsetName;
    }

}
