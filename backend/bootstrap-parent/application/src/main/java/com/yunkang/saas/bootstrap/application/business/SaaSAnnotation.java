package com.yunkang.saas.bootstrap.application.business;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SaaSAnnotation {
}
