package me.zhyx.common.annotation;

import java.lang.annotation.*;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String value();
}
