package com.digest.factory.factory;

import com.digest.factory.product.DefaultEncoder;
import com.digest.factory.product.IEncoder;

import java.security.NoSuchAlgorithmException;

public class DefaultEncoderFactory implements IEncoderFactory{

    @Override
    public IEncoder getInstance(String alg, String charsetName) throws NoSuchAlgorithmException {
        return new DefaultEncoder(alg, charsetName);
    }

}
