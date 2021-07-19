package com.digest.factory.factory;

import com.digest.factory.config.EncoderProperties;
import com.digest.factory.product.IEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encoders {

    public static IEncoder getEncoderByConfig() {
        try {
            EncoderProperties encoderProperties = EncoderProperties.getEncoderProperties();
            String factory = encoderProperties.getEncoderFactory();
            String charsetName = encoderProperties.getCharsetName();
            String algorithm = encoderProperties.getAlgorithm();
            Class clazz =  Class.forName(factory);
            IEncoderFactory encoderF = (IEncoderFactory) clazz.newInstance();
            return encoderF.getInstance(algorithm, charsetName);
        } catch (IllegalAccessException | NoSuchAlgorithmException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IEncoder getEncoderByAlgorithm(String algorithm){
        return null;
    }
}
