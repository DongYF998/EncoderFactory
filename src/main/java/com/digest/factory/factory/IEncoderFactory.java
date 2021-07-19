package com.digest.factory.factory;

import com.digest.factory.product.IEncoder;

import java.security.NoSuchAlgorithmException;

/**
 *  加密算法工厂
 */
public interface IEncoderFactory {

    /**
     * 通过I
     * @param alg 算法
     * @param charsetName 字符集
     * @return IEncoder 算法接口
     * @throws NoSuchAlgorithmException 无此算法异常
     */
    IEncoder getInstance(String alg, String charsetName) throws NoSuchAlgorithmException;
}
