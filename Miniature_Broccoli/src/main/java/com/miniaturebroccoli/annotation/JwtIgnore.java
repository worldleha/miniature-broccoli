package com.miniaturebroccoli.annotation;

import java.lang.annotation.*;

/**
 * JWT验证忽略注解
 * @author scc
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
