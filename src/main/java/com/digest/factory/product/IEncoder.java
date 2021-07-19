package com.digest.factory.product;


import java.io.UnsupportedEncodingException;

public interface IEncoder {


    /**
     * 获取当前加密算法
     * @return 当前加密算法
     */
    String getAlgorithm();

    /**
     * 加密方法
     * @param userName 用户名
     * @param plaintextPwd 密码明文
     * @return 加密后的密码密文
     * @throws UnsupportedEncodingException
     */
    String encode(String userName, String plaintextPwd) throws UnsupportedEncodingException;

    /**
     * 获取加密头
     * @return 返回加密头
     */
    String getHeader();

    /**
     * 获取字符集
     * @return 当前字符集
     */
    String getCharsetName();
}
