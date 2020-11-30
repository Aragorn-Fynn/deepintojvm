/**
 * 源码来自：https://liuyehcf.github.io/2018/02/02/Java-JSR-269-%E6%8F%92%E5%85%A5%E5%BC%8F%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86%E5%99%A8/
 */
package com.feng.deepintojvm.annotation.src.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来生成无参构造函数
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface NoArgsConstructor {
}
