package com.digest.factory.config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EncoderProperties {

    private String encoderFactory;

    private String charsetName;

    private String algorithm;

    private EncoderProperties(){}

    private EncoderProperties(String encoderFactory, String charsetName, String algorithm){
        this.encoderFactory = encoderFactory;
        this.charsetName = charsetName;
        this.algorithm = algorithm;
    }

    public static EncoderProperties getEncoderProperties(){
        Properties props = null;
        InputStream is = null;
        String fileName = "config/encoder.properties";
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
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
