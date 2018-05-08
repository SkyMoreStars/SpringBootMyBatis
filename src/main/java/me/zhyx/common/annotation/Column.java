package me.zhyx.common.annotation;

import java.lang.annotation.*;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value();
    boolean isCondition() default false;
}
