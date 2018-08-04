package com.vip.spring.formework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.TYPE})  //类、接口（包括注释类型）或枚举声明
@Retention(RetentionPolicy.RUNTIME) // 编译器将Annotation存储于class文件中，并且可由JVM读入
@Documented //类和方法的Annotation在缺省情况下是不出现在javadoc中的。如果使用@Documented修饰该Annotation，则表示它可以出现在javadoc中。
//定义Annotation时，@Documented可有可无；若没有定义，则Annotation不会出现在javadoc中
public @interface Controller {
    String value() default "";
}
