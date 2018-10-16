package com.yunkang.saas.security.local.business.authorize.controller;

import java.lang.annotation.*;

/**
 * 给对象设置tid字段
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tenantable {

    /**是否处理包含的其他属性*/
    boolean deep() default false;
}
