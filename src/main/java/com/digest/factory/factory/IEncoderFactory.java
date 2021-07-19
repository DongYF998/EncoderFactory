package com.digest.factory.factory;

import com.digest.factory.product.IEncoder;

import java.security.NoSuchAlgorithmException;

public interface IEncoderFactory {

    IEncoder getInstance(String alg, String charsetName) throws NoSuchAlgorithmException;
}
