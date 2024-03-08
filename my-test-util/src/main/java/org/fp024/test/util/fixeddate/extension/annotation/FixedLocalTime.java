package org.fp024.test.util.fixeddate.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** LocalTime 시간 고정 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FixedLocalTime {
  int hour() default 0;

  int minute() default 0;

  int second() default 0;

  int nanoSecond() default 0;
}
