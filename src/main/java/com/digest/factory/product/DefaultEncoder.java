package com.digest.factory.product;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 默认Encoder, 支持MD2 MD5 SHA-1 SHA-256 加密算法
 */
public class DefaultEncoder implements IEncoder {

    /** 默认加密信息摘要 */
    private final MessageDigest messageDigest;

    /** 算法 */
    private final String algorithm;

    /** 字符集 */
    private final String charsetName;

    /**
     *  构造器
     * @param algorithm  算法
     * @param charsetName 字符集
     * @throws NoSuchAlgorithmException
     */
    public DefaultEncoder(String algorithm, String charsetName) throws NoSuchAlgorithmException {
        // 默认SHA-1算法
        String _algorithm = (algorithm == null || "".equals(algorithm)) ? "SHA-1" : algorithm;
        // // 默认UTF-8 字符集
        String _charsetName = (charsetName == null || "".equals(charsetName)) ? "UTF-8" : charsetName;
        this.messageDigest = MessageDigest.getInstance(_algorithm);
        this.algorithm = _algorithm;
        this.charsetName = _charsetName;
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

    /**
     * 获取加密头
     * @return 返回加密头
     */
    @Override
    public String getHeader() {
        return "";
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
