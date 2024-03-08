package org.fp024.test.util.fixeddate.extension;

import static org.mockito.Mockito.mockStatic;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalDate;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalDateTime;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalTime;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/** 날짜 고정 Junit 5 Extension */
public class FixedDateExtension implements BeforeEachCallback, AfterEachCallback {

  private MockedStatic<LocalDateTime> localDateTimeMockedStatic;

  private MockedStatic<LocalDate> localDateMockedStatic;

  private MockedStatic<LocalTime> localTimeMockedStatic;

  @Override
  public void beforeEach(ExtensionContext context) {
    // 현재 실행 중인 테스트 메서드 얻기
    Optional<Method> testMethod = context.getTestMethod();
    if (testMethod.isPresent()) {
      Method method = testMethod.get();

      // ✨ @FixedLocalDateTime 어노테이션 처리
      FixedLocalDateTime fixedLocalDateTime = method.getAnnotation(FixedLocalDateTime.class);
      if (fixedLocalDateTime != null) {
        LocalDateTime newNow =
            LocalDateTime.of(
                fixedLocalDateTime.year(), //
                fixedLocalDateTime.month(),
                fixedLocalDateTime.dayOfMonth(),
                fixedLocalDateTime.hour(),
                fixedLocalDateTime.minute(),
                fixedLocalDateTime.second(),
                fixedLocalDateTime.nanoSecond());
        localDateTimeMockedStatic = mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(newNow);
      }

      // ✨ @FixedLocalDate 어노테이션 처리
      FixedLocalDate fixedLocalDate = method.getAnnotation(FixedLocalDate.class);
      if (fixedLocalDate != null) {
        LocalDate newNow =
            LocalDate.of(
                fixedLocalDate.year(), //
                fixedLocalDate.month(),
                fixedLocalDate.dayOfMonth());
        localDateMockedStatic = mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS);
        localDateMockedStatic.when(LocalDate::now).thenReturn(newNow);
      }

      // ✨ @FixedLocalTime 어노테이션 처리
      FixedLocalTime fixedLocalTime = method.getAnnotation(FixedLocalTime.class);
      if (fixedLocalTime != null) {
        LocalTime newNow =
            LocalTime.of(
                fixedLocalTime.hour(), //
                fixedLocalTime.minute(),
                fixedLocalTime.second(),
                fixedLocalTime.nanoSecond());
        localTimeMockedStatic = mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS);
        localTimeMockedStatic.when(LocalTime::now).thenReturn(newNow);
      }
    }
  }

  @Override
  public void afterEach(ExtensionContext context) {
    if (localDateTimeMockedStatic != null) {
      localDateTimeMockedStatic.close();
      localDateTimeMockedStatic = null;
    }

    if (localDateMockedStatic != null) {
      localDateMockedStatic.close();
      localDateMockedStatic = null;
    }

    if (localTimeMockedStatic != null) {
      localTimeMockedStatic.close();
      localTimeMockedStatic = null;
    }
  }
}
