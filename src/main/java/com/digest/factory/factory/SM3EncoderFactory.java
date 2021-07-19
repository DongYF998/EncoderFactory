package com.digest.factory.factory;

import com.digest.factory.product.DefaultEncoder;
import com.digest.factory.product.IEncoder;
import com.digest.factory.product.SM3Encoder;

import java.security.NoSuchAlgorithmException;

public class SM3EncoderFactory implements IEncoderFactory{

    @Override
    public IEncoder getInstance(String alg, String charsetName) throws NoSuchAlgorithmException {
        return new SM3Encoder(alg, charsetName);
    }
}
