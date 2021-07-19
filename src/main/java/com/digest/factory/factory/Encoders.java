package com.digest.factory.factory;

import com.digest.factory.config.EncoderProperties;
import com.digest.factory.product.DefaultEncoder;
import com.digest.factory.product.IEncoder;
import com.digest.factory.product.SM3Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encoders {

    /**
     * 通过匹配文件获取加密算法
     * @return IEncoder 接口的实现类
     */
    public static IEncoder getEncoderByConfig() {
        try {
            // 获取配置文件
            EncoderProperties encoderProperties = EncoderProperties.getEncoderProperties();
            // 获取加密类工厂
            String factory = encoderProperties.getEncoderFactory();
            // 获取字符集
            String charsetName = encoderProperties.getCharsetName();
            // 获取算法
            String algorithm = encoderProperties.getAlgorithm();
            // 通过反射获取工厂类 并创建实实例
            Class clazz =  Class.forName(factory);
            IEncoderFactory encoderF = (IEncoderFactory) clazz.newInstance();
            // 通过算法和字符集获取加密类
            return encoderF.getInstance(algorithm, charsetName);
        } catch (IllegalAccessException | NoSuchAlgorithmException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过算法名称获取Encoder, 并设置为字符集
     * @param algorithm 算法名称
     * @param charsetName 字符集
     * @return Encoder
     * @throws NoSuchAlgorithmException
     */
    public static IEncoder getEncoderByAlgorithm(String algorithm, String charsetName) throws NoSuchAlgorithmException {
        if("SM3".equals(algorithm)){
            return new SM3Encoder(algorithm, charsetName);
        }else {
            return new DefaultEncoder(algorithm, charsetName);
        }
    }

    /**
     * 通过加密头获取算法类型
     * @param cipherText
     * @return
     */
    public static IEncoder getEncoderByHeader(String cipherText) throws NoSuchAlgorithmException {
        if(cipherText.startsWith("$SM3$")){
            return new SM3Encoder();
        }else {
            return new DefaultEncoder();
        }
    }
}
