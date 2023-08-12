package org.fp024.test.util.fixeddate;

import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fp024.test.util.common.ExceptionableRunnable;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * LocalDateTime, LocalDate 의 날짜/시간을 고정해서 <br>
 * 테스트를 지원하는 유틸리티 메서드 모음
 *
 * <p>클래스명만 다르고 메서드 로직은 같은데.. 정적 메서드들 제네릭을 사용한 메서드 공통화는 힘들 것 같다.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixedDateTestHelper {
  /** LocalDateTime의 현재 날짜, 시간을 변경해서 내부 코드 실행 */
  public static void changeNowLocalDateTime(LocalDateTime newNow, ExceptionableRunnable r) {
    try (MockedStatic<LocalDateTime> mockedJSONContext =
        mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
      mockedJSONContext.when(LocalDateTime::now).thenReturn(newNow);
      r.run();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  /** LocalDate의 현재 날짜를 변경해서 내부 코드 실행 */
  public static void changeNowLocalDate(LocalDate newNow, ExceptionableRunnable r) {
    try (MockedStatic<LocalDate> mockedJSONContext =
        mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
      mockedJSONContext.when(LocalDate::now).thenReturn(newNow);
      r.run();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  /** LocalTime의 현재 시간을 변경해서 내부 코드 실행 */
  public static void changeNowLocalTime(LocalTime newNow, ExceptionableRunnable r) {
    try (MockedStatic<LocalTime> mockedJSONContext =
        mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
      mockedJSONContext.when(LocalTime::now).thenReturn(newNow);
      r.run();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
