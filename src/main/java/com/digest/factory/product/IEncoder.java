package com.digest.factory.product;


import java.io.UnsupportedEncodingException;

public interface IEncoder {

    String getAlgorithm();

    String encode(String userName, String plaintextPwd) throws UnsupportedEncodingException;

    String getHeader();

    String getCharsetName();
}
