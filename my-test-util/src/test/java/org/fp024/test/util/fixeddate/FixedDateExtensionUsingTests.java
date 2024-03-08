package org.fp024.test.util.fixeddate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
}
