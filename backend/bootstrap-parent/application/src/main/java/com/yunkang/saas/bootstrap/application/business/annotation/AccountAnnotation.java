package com.yunkang.saas.bootstrap.application.business.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccountAnnotation {


    boolean autoCondition() default true;

    /**
     * (可选) 如果用在查询请求上, 并且设置了条件类的class,当查询条件为空的时候，会默认创建一个条件对象
     */
    Class conditionClass() default Object.class;
}
