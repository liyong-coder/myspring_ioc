package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:00 PM
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
public @interface IocAutoWired {
}
