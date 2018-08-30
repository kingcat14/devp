package com.kingzoo.kingcat.project.icode4.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class User implements HealthIndicator {

    Health userHealth;

    /**
     * user监控 访问: http://localhost:8088/health
     *
     * @return 自定义Health监控
     */

    @PostConstruct
    public void init(){
        userHealth = new Health.Builder().withDetail("usercount", 10) //自定义监控内容
                .withDetail("userstatus", "up").up().build();
    }

    @Override
    public Health health() {

        return new Health.Builder().withDetail("usercount", new Random().nextInt()+"") //自定义监控内容
                .withDetail("userstatus", "up").up().build();
    }

}