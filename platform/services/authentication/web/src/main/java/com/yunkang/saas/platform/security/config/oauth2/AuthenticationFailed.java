package com.yunkang.saas.platform.security.config.oauth2;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationFailed {

    String error;
    String message;
    String path;
    Long timestamp;
}
