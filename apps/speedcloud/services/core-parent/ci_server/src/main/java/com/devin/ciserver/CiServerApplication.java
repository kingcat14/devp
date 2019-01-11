package com.devin.ciserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by devin on 2018/8/8
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration
//@ComponentScan("com.devin.ciserver")
public class CiServerApplication {
    public static void main(String[] args){
        SpringApplication.run(CiServerApplication.class, args);
    }
}