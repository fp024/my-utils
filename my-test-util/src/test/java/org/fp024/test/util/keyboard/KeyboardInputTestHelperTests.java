package org.fp024.test.util.keyboard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

/** KeyboardInputTestHelper 의 사용 예제 테스트 */
class KeyboardInputTestHelperTests extends KeyboardInputTestHelper {

  /** 사용 예제 클래스 */
  static class KeyboardInputTestExample {
    static int[] run(int size) {
      int[] array = new int[size];

      try (Scanner sc = new Scanner(System.in)) {
        for (int i = 0; i < array.length; i++) {
          array[i] = sc.nextInt();
        }
        return array;
      }
    }
  }

  @Test
  void testKeyboardInput() {
    setKeyboardInput("1" + ENTER + "2 3 4 5");

    int[] result = KeyboardInputTestExample.run(5);

    assertThat(result)
        .isNotEmpty()
        .hasSize(5) //
        .isEqualTo(new int[] {1, 2, 3, 4, 5});

    setKeyboardInput("4 3 2" + ENTER + "1");

    result = KeyboardInputTestExample.run(4);

    assertThat(result)
        .isNotEmpty()
        .hasSize(4) //
        .isEqualTo(new int[] {4, 3, 2, 1});
  }
}
