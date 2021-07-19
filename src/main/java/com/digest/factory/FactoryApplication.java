package com.digest.factory;

import com.digest.factory.factory.Encoders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class FactoryApplication {

    public static void main(String[] args){
        SpringApplication.run(FactoryApplication.class, args);
    }

}
