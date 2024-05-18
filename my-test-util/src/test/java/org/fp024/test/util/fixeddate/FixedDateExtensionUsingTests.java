package org.fp024.test.util.fixeddate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.fp024.test.util.fixeddate.extension.FixedDateExtension;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalDate;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalDateTime;
import org.fp024.test.util.fixeddate.extension.annotation.FixedLocalTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(FixedDateExtension.class)
class FixedDateExtensionUsingTests {
  // ---------- ✨ FixedLocalDateTime 테스트 ✨ ----------
  @FixedLocalDateTime(year = 2024, month = 3, dayOfMonth = 8)
  @Test
  void testFixedLocalDateTime01() {
    assertThat(LocalDateTime.now()) //
        .isEqualTo(LocalDateTime.of(2024, 3, 8, 0, 0, 0));
  }

  @Test
  void testFixedLocalDateTime02() {
    LOGGER.info("{}", LocalDateTime.now());
  }

  @FixedLocalDateTime(year = 2023, month = 12, dayOfMonth = 25, hour = 12, minute = 5, second = 10)
  @Test
  void testFixedLocalDateTime03() {
    assertThat(LocalDateTime.now()) //
        .isEqualTo(LocalDateTime.of(2023, 12, 25, 12, 5, 10, 0));
  }

  // ---------- ✨ FixedLocalDate 테스트 ✨ ----------
  @FixedLocalDate(year = 2024, month = 3, dayOfMonth = 8)
  @Test
  void testFixedLocalDate01() {
    assertThat(LocalDate.now()) //
        .isEqualTo(LocalDate.of(2024, 3, 8));
  }

  @Test
  void testFixedLocalDate02() {
    LOGGER.info("{}", LocalDate.now());
  }

  @FixedLocalDate(year = 2023, month = 12, dayOfMonth = 25)
  @Test
  void testFixedLocalDate03() {
    assertThat(LocalDate.now()) //
        .isEqualTo(LocalDate.of(2023, 12, 25));
  }

  // ---------- ✨ FixedLocalTime 테스트 ✨ ----------
  @FixedLocalTime(hour = 12, minute = 1)
  @Test
  void testFixedLocalTime01() {
    assertThat(LocalTime.now()) //
        .isEqualTo(LocalTime.of(12, 1));
  }

  @Test
  void testFixedLocalTime02() {
    LOGGER.info("{}", LocalTime.now());
  }

  @FixedLocalTime(hour = 1, minute = 20)
  @Test
  void testFixedLocalTime03() {
    assertThat(LocalTime.now()) //
        .isEqualTo(LocalTime.of(1, 20));
  }

  /*
    💡Thread가 다르면 Mocking이 안되는 것을 확인할 수 있는데,
       아래의 테스트 코드는,
       Runnable 블록 내에서 발생한 assertion예외가 밖으로 전파되지 않음.

       JUnit은 기본적으로 메인스레드에서 테스트를 실행하고,
       별도에 스레드에서 발생하는 예외는 JUnit에서 캐치되지 않음.
       그러므로 별도의 스레드를 메인스레드로 전파해야됨.

       FutureTask는 Runnable을 받아들이며, Runnable에서 발생하는 예외를 캡쳐하여,
       나중에 메인스레드에서 확인할 수 있게함.
  */
  @FixedLocalTime(hour = 1, minute = 20)
  @Test
  void testFixedLocalTime_Other_Thread() throws Exception {
    Runnable r =
        () -> {
          // 💡 검증 예외는 발생하지만 메인스레드로 전파되지 않음.
          assertThat(LocalTime.now()) //
              .isEqualTo(LocalTime.of(1, 20));
        };

    Thread t1 = new Thread(r);
    t1.start();
    t1.join();
  }

  @FixedLocalTime(hour = 1, minute = 20)
  @Test
  void testFixedLocalTime_Other_Thread_02() throws Exception {
    FutureTask<Void> futureTask =
        new FutureTask<>(
            () -> {
              assertThat(LocalTime.now()).isEqualTo(LocalTime.of(1, 20));
              return null;
            });

    Thread t1 = new Thread(futureTask);
    t1.start();
    t1.join();

    // 이 부분에서 별도의 스레드에서 발생한 예외를 확인하고 처리함.
    assertThatThrownBy(() -> futureTask.get())
        .hasCauseInstanceOf(org.opentest4j.AssertionFailedError.class);
  }

  @FixedLocalTime(hour = 1, minute = 20)
  @Test
  void testFixedLocalTime_Other_Thread_03() throws Exception {

    Future<?> executor =
        Executors.newSingleThreadExecutor()
            .submit(
                () -> {
                  assertThat(LocalTime.now()) //
                      .isEqualTo(LocalTime.of(1, 20));
                });

    assertThatThrownBy(() -> executor.get())
        .hasCauseInstanceOf(org.opentest4j.AssertionFailedError.class);
  }
}
