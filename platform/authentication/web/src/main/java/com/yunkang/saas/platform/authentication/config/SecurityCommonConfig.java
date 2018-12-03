package com.yunkang.saas.platform.authentication.config;

import com.yunkang.saas.platform.authentication.business.oauth2.token.RedisTemplateTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 各应用和服务都要使用的通用安全配置
 */
@Configuration
public class SecurityCommonConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey("neusoft");
//        return accessTokenConverter;
//    }

    @Bean
    public RedisTemplateTokenStore redisTokenStore(RedisTemplate redisTemplate) {
        RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore();
        redisTemplateStore.setRedisTemplate(redisTemplate);
        return redisTemplateStore;
    }
}
