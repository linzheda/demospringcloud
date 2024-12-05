package com.linzd.basecore.annotation;

/**
 * 描述 操作日志注解
 *
 * @author Lorenzo Lin
 * @created 2020年09月10日 20:23
 */

import com.linzd.basecore.common.enums.OperType;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperLog {
    String modul() default ""; // 操作模块
    OperType type() default OperType.OTHER;  // 操作类型
    String desc() default "";  // 操作说明
}
