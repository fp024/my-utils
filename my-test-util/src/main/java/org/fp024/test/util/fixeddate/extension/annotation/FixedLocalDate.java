package org.fp024.test.util.fixeddate.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** LocalDate 시간 고정 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FixedLocalDate {
  int year();

  int month();

  int dayOfMonth();
}
