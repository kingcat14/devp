package com.yunkang.saas.platform.security.config.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @author fei.yao
 * 密码加密匹配配置
 */
@Configuration
public class PasswordConfig {
    private static final String DEFAULT_PASSWORD_ENCODE = "$2a$10$pG2NzSjkqcg2qhuJHlf69.Jb82.EZagKAHs4WLHrOHsbY739uT2Eu";
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return super.encode(rawPassword);
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                if (super.matches(rawPassword, DEFAULT_PASSWORD_ENCODE)) {
//                    return true;
//                }
//                return super.matches(rawPassword, encodedPassword);
//            }
//        };
        return new StandardPasswordEncoder();
    }
}
