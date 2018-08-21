package com.yunkang.saas.platform.business.application;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SaaSAnnotation {
}
