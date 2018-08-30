package com.kingzoo.kingcat.project.icode4;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class TestPassword {

    PasswordEncoder passwordEncoder;
    /**
     *
     */
    @Test
    public void aa(){
        String a = "e10adc3949ba59abbe56e057f20f883e";
        passwordEncoder = new MessageDigestPasswordEncoder("MD5");

        boolean flag = passwordEncoder.matches("123456", a);
        System.out.println(flag);

    }
}
