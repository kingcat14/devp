package net.aicoder.speedcloud.icode.config;


import com.yunkang.saas.bootstrap.security.common.business.token.RedisTemplateTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * 各应用和服务都要使用的通用安全配置
 */
//@Configuration
//@Import(CommonSecurityConfig.class)
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${spring.application.name}")
    private String resourceId;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceServerProperties.getResourceId()).stateless(true);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Primary
    @Bean
    public RemoteTokenServices tokenServices(){

        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setRestTemplate(restTemplate);
        tokenService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        tokenService.setClientId(resourceServerProperties.getClientId());
        tokenService.setClientSecret(resourceServerProperties.getClientSecret());
        return tokenService;
    }


    @Bean
    public RedisTemplateTokenStore redisTokenStore(RedisTemplate redisTemplate) {
        RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore();
        redisTemplateStore.setRedisTemplate(redisTemplate);
        return redisTemplateStore;
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey("neusoft");
//        return accessTokenConverter;
//    }
//    @Bean
//    public JwtTokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }

}
