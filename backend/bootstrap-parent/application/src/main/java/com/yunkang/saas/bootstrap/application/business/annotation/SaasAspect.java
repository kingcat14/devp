package com.yunkang.saas.bootstrap.application.business.annotation;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 处理SaaS参数的注入问题
 */
@Aspect //AOP 切面
@Component
public class SaasAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaasAspect.class);


    @Autowired(required = false)
    private SaaSUtil saaSUtil;

    @Pointcut("@annotation(com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation)")
    public  void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){

        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        SaaSAnnotation annotation = method.getAnnotation(SaaSAnnotation.class);


        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            System.out.println(arg.getClass().getName());
            if(arg instanceof PageSearchRequest) {
                aaa((PageSearchRequest) arg, annotation.conditionClass());
            }
            else{
                aaa(arg);
            }
        }

//        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
//        Method method = sign.getMethod();
//        OperateLog annotation = method.getAnnotation(OperateLog.class);
//        System.out.print("打印："+annotation.value()+" 前置日志");
    }


    private void aaa(Object object){

        Method method = ReflectionUtils.findMethod(object.getClass(), "setTid", Long.class);

        if(method == null){
            System.out.println("-------------NO setId(String) method");
            return;
        }

        System.out.println("-------------HAVE setId(String) method");

        if(saaSUtil != null){
            ReflectionUtils.invokeMethod(method, object, saaSUtil.getAccount().getTid());
        }

    }


    private void aaa(PageSearchRequest pageSearchRequest, Class clazz){

        System.out.println(pageSearchRequest.getClass().getName());

        tryFillCondition(pageSearchRequest, clazz);

        Object searchCondition = pageSearchRequest.getSearchCondition();

        if(searchCondition != null){
            aaa(searchCondition);
        }

    }

    private void tryFillCondition(PageSearchRequest pageSearchRequest, Class clazz){
        if(clazz.equals(Object.class)){
            return;
        }
        if(pageSearchRequest.getSearchCondition() != null){
            return;
        }
        try {
            Object o = clazz.newInstance();
            pageSearchRequest.setSearchCondition(o);
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
