package com.digest.factory;

import com.digest.factory.config.EncoderProperties;
import com.digest.factory.factory.Encoders;
import com.digest.factory.product.IEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class FactoryApplicationTests {


    @Test
    void contextLoads() throws UnsupportedEncodingException {
        IEncoder encoder = Encoders.getEncoderByConfig();
        String hello = encoder.encode("hello", "1234");
        System.out.println(hello);

    }

}
