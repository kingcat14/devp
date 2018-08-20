package net.aicoder.devp.maintenance.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableJpaRepositories(basePackages = {"net.aicoder.devp.maintenance"})
@EntityScan({"net.aicoder.devp.maintenance"})
public class BusinessConfig {


//    @Bean
//    public ConversionServiceFactoryBean conversionService(){
//        ConversionServiceFactoryBean conversionServiceFactoryBean=new ConversionServiceFactoryBean();
//        Set<Converter> converters=new HashSet<>();
//        converters.add(new DateConverter());//日期转换器
//        conversionServiceFactoryBean.setConverters(converters);
//        return conversionServiceFactoryBean;
//    }
}
