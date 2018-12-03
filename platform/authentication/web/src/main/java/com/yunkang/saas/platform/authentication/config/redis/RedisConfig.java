package com.yunkang.saas.platform.authentication.config.redis;


import com.yunkang.saas.platform.authentication.config.redis.util.RedisObjectSerializer;
import com.yunkang.saas.platform.authentication.config.redis.util.SpringCacheKeyGenerator;
import com.yunkang.saas.platform.authentication.config.redis.util.SpringCacheResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年04月23日 下午20:01:06 类说明
 */
@Configuration
public class RedisConfig {

    @Primary
    @Bean("redisTemplate")
    // 没有此属性就不会装配bean 如果是单个redis 将此注解注释掉
    @ConditionalOnProperty(name = "spring.redis.cluster.nodes", matchIfMissing = false)
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        RedisSerializer stringSerializer = new StringRedisSerializer();
//        RedisSerializer redisObjectSerializer = new RedisObjectSerializer();
        RedisSerializer redisObjectSerializer = new RedisObjectSerializer();
        redisTemplate.setKeySerializer(stringSerializer); // key的序列化类型
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(redisObjectSerializer); // value的序列化类型
        redisTemplate.afterPropertiesSet();

//        redisTemplate.opsForValue().set("hello", "wolrd");
        return redisTemplate;
    }

    @Primary
    @Bean("redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.host", matchIfMissing = true)
    public RedisTemplate<String, Object> getSingleRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer()); // value的序列化类型
        redisTemplate.afterPropertiesSet();

        //        redisTemplate.opsForValue().set("hello", "wolrd");
        return redisTemplate;
    }


}

@Configuration
//@ComponentScan(basePackages = "com.yunkang.saas")
@EnableCaching(proxyTargetClass = true)
class SpringCacheConfig implements CachingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCacheConfig.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//        rcm.setUsePrefix(true);
        //设置缓存过期时间
//        rcm.setDefaultExpiration(60);//秒，便于测试
        return rcm;
    }

    @Bean("springCacheResolver")
    @Override
    public SpringCacheResolver cacheResolver() {
        return new SpringCacheResolver();
    }

    @Bean("springCacheKeyGenerator")
    @Override
    public SpringCacheKeyGenerator keyGenerator() {
        return new SpringCacheKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.warn("cache get error:" + key.toString(), exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                LOGGER.warn("cache put error:" + key.toString(), exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.warn("cache evict error:" + key.toString(), exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                LOGGER.warn("cache clear error:", exception);
            }
        };
    }
}
