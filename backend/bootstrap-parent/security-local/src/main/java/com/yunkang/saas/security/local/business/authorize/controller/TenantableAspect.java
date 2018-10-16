package com.yunkang.saas.security.local.business.authorize.controller;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TenantableAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.yunkang.saas.common.framework.tenant.annotation.TenantableAspect.class);

    @Pointcut("@annotation(com.yunkang.saas.common.framework.tenant.annotation.Tenantable)")
    public void annotationPointCut() {
        LOGGER.debug("Pointcut: Tenantable");
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {


//        SignatureAttribute.MethodSignature sign =  (SignatureAttribute.MethodSignature)joinPoint.getSignature();
//
//        Method method = sign.getMethod();
//        OperateLog annotation = method.getAnnotation(OperateLog.class);
//        System.out.print("打印："+annotation.value()+" 前置日志");


        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Object arg = joinPoint.getArgs()[i];
            System.out.println("arg class:"+arg.getClass().getName());
        }
        System.out.println(joinPoint.getSignature().getName());
        System.out.println("=====checkSecurity====");


    }


}
