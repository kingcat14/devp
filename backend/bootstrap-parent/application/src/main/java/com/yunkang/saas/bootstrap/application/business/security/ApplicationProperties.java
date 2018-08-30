package com.yunkang.saas.bootstrap.application.business.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter @Setter
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private String product;
    private String module;
    @Value("${application.code:-1}")
    private String code;
    private String name;
    private String uploadFilePath;

}
