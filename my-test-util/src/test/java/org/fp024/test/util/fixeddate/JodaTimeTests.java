package org.fp024.test.util.fixeddate;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
   예전 Java 6 프로젝트 환경에서는 Java 프로젝트의 날짜 처리에 JodaTime의 클래스를 썻었음.
    JodaTime 을 사용할 때는 Mockito를 사용할 필요가 없어서 좋긴했음. 😅
*/
@Slf4j
class JodaTimeTests {
  private final LocalDateTime dateTime =
      new LocalDateTime() //
          .withDate(2023, 8, 13)
          .withTime(1, 2, 3, 4);

  @BeforeEach
  void beforeEach() {
    // 테스트 날짜 고정
    DateTimeUtils.setCurrentMillisFixed(dateTime.toDate().getTime());
  }

  @Test
  void test() {
    String dateTimeString =
        LocalDateTime.now() //
            .toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss.SSS"));

    assertThat(dateTimeString).isEqualTo("2023-08-13 01:02:03.004");
  }

  @AfterEach
  void afterEach() {
    // 테스트 메서드 끝나면 날짜 원상 복구
    DateTimeUtils.setCurrentMillisSystem();
  }
}
