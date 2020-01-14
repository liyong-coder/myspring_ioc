package com.spring.annotation;

import java.lang.annotation.*;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:01 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IocComponent {
}
