package  net.aicoder.speedcloud.icode.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yunkang.saas.bootstrap.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(CommonConfig.class)
public class IcodeWebConfig {

    @ConditionalOnClass(ObjectMapper.class)
    @Autowired
    public void convertLongToString(ObjectMapper objectMapper){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        //不这么写，不能用PATCH方法
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

}