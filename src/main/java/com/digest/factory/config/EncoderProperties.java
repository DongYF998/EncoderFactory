package com.digest.factory.config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EncoderProperties {

    private String encoderFactory;

    private String charsetName;

    private String algorithm;

    private static final String CONFIG_FILE_PATH = "config/encoder.properties";

    private EncoderProperties(){}

    private EncoderProperties(String encoderFactory, String charsetName, String algorithm){
        this.encoderFactory = encoderFactory;
        this.charsetName = charsetName;
        this.algorithm = algorithm;
    }

    /**
     * 通过配置文件 获取配置
     * @return EncoderProperties 加密配置
     */
    public static EncoderProperties getEncoderProperties(){
        Properties props = null;
        InputStream is = null;
        // 配置文件位置
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
            if (is == null) {
                throw new FileNotFoundException(CONFIG_FILE_PATH + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new EncoderProperties(props.getProperty("encoderFactory"),props.getProperty("charsetName"), props.getProperty("algorithm"));
    }

    public String getEncoderFactory() {
        return encoderFactory;
    }

    public void setEncoderFactory(String encoderFactory) {
        this.encoderFactory = encoderFactory;
    }

    public String getCharsetName() {
        return charsetName;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String toString() {
        return "EncoderProperties{" +
                "encoderFactory='" + encoderFactory + '\'' +
                ", charsetName='" + charsetName + '\'' +
                ", algorithm='" + algorithm + '\'' +
                '}';
    }
}
