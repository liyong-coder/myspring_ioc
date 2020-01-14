package com.spring.annotation;

import java.lang.annotation.*;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:02 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@IocComponent
public @interface IocService {
}
