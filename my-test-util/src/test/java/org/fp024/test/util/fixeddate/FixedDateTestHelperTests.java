package org.fp024.test.util.fixeddate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/** now() 메서드 들에 대한 고정 시간 테스트 */
class FixedDateTestHelperTests {
  @Test
  void testAfter12h() {
    LocalTime now = LocalTime.of(12, 0, 0, 1);

    FixedDateTestHelper.changeNowLocalTime(
        now, () -> assertThat(LocalTime.now().isAfter(LocalTime.of(12, 0))).isTrue());
  }

  @Test
  void testEqual12h() {
    LocalTime now = LocalTime.of(12, 0, 0, 0).minusNanos(1);

    FixedDateTestHelper.changeNowLocalTime(
        now, () -> assertThat(LocalTime.now().isAfter(LocalTime.of(12, 0))).isFalse());
  }

  @Test
  void testBefore12h() {
    LocalTime now = LocalTime.of(12, 0, 0, 0).minusNanos(1);

    FixedDateTestHelper.changeNowLocalTime(
        now, () -> assertThat(LocalTime.now().isAfter(LocalTime.of(12, 0))).isFalse());
  }

  @Test
  void testChangeNowLocalDateTime() {
    LocalDateTime now = LocalDateTime.of(2023, 8, 12, 1, 2, 3, 4);
    FixedDateTestHelper.changeNowLocalDateTime(
        now, () -> assertThat(LocalDateTime.now().equals(now)).isTrue());
  }

  @Test
  void testChangeNowLocalDate() {
    LocalDate now = LocalDate.of(2023, 8, 12);
    FixedDateTestHelper.changeNowLocalDate(
        now, () -> assertThat(LocalDate.now().equals(now)).isTrue());
  }

  @Test
  void testChangeNowLocalTime() {
    LocalTime now = LocalTime.of(1, 2, 3, 4);
    FixedDateTestHelper.changeNowLocalTime(
        now, () -> assertThat(LocalTime.now().equals(now)).isTrue());
  }
}
