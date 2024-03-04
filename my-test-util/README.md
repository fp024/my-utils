# my-test-util

> 테스트 헬퍼 클래스들을 모아둔 모듈...
>
> 테스트할 때 중복해서 만들 가능성이 있는 헬퍼 클래스들을 모아두면 되겠다. 👍
>
> 
>
> #### 1. fixeddate 패키지
>
> JodaTime을 사용할 때는, 날짜를 고정해주는 유틸리티 메서드가 있어서 사용하기 편했는데, 
>
> Java 8 부터 추가된 날짜클래스에서는 now() 정적 메서드를 Mocking하는 것외엔 방법을 못찾았음. 😅
>
> 이 테스트 유틸리티는 Mockito를 사용해 고정된 날짜로 테스트를 쉽게 도와주는 유틸리티 클래스를 포함함.
>
> 
>
> #### 2. keyboard 패키지
>
> 알고리즘 테스트를 하면 보통 키보드 입력이 들어가고 결과를 검증하는 식인데, JUnit 에서 키보드 입력을 미리 넣어주고 실행하도록 하기위해 헬퍼클래스를 만들었었다. 그것도 쓸일이 모르니 여기에 넣어둠 ...😅





## Maven 로컬레파지토리 인스톨

* `publishToMavenLocal` 테스크 실행

  ```sh
  gradle clean test publishToMavenLocal
  ```

* 위 명령을 실행하면 Maven 로컬 레파지토리에 `fixed-date-test-util` 라이브러리가 설치된다.

* https://docs.gradle.org/current/userguide/publishing_maven.html

  

## 사용처 프로젝트에서 적용 방법

* `build.gradle`

  ```groovy
  repositories {
    // mavenLocal을 사용할 때는 content 필터링을 해줘야한다.
    mavenLocal {    
      content {
        includeGroup "org.fp024.util"
      }
    }
    mavenCentral()
    ...
  }
  ...
  
  dependencies {
    ... // 모듈의 디펜던시 추가
    testImplementation 'org.fp024.test.util:my-test-util:0.0.1-SNAPSHOT'
    ...
  }
  ```
  
  
