# vault-util (이전 버전 호환용)

> 💡 스프링 5.x, 스프링 부트 2.x 호환 버전
>
> * `2.0.0-SNAPSHOT` 버전을 이전 버전 호환용으로 쓰기로 했다.

#### 

## Maven 로컬레파지토리 인스톨

* `publishToMavenLocal` 테스크 실행

  ```sh
  gradle clean test publishToMavenLocal
  ```

* 위 명령을 실행하면 Maven 로컬 레파지토리에 `vault-util` 라이브러리가 설치된다.

* https://docs.gradle.org/current/userguide/publishing_maven.html

  

## 사용처 프로젝트에서 적용 방법

* build.gradle

  ```groovy
  repositories {
    mavenLocal()  // maven 로컬 레파지토리 추가
    mavenCentral()
    ...
  }
  ...
  
  dependencies {
    ... // 모듈의 디펜던시 추가
    implementation 'org.fp024.util:vault-util:2.0.0-SNAPSHOT'
    ...
  }
  ```

  

---

## Todo

[`vault-util`](../vault-util)와 비교해서 [spring-vault-core](https://mvnrepository.com/artifact/org.springframework.vault/spring-vault-core) 버전만 다르고 나머지는 동일하다.

스터디 프로젝트 중에, Spring 5, Spring Boot 2 프로젝트에서 사용을 위해서는 spring-vault-core의 버전을 `2.x`대로 낮추는것이 나아서 만들게되었다. 😅

처음에는 classifier로 나눌 수 있을까 했는데... 잘되지 않았고 더 복잡해져서 프로젝트를 나눠버렸다.

이 레파지토리에 다른 라이브러리도 있어서 브렌치로 만들수도 없어서 프로젝트를 하나더 중복으로 만들어버림..

- [ ] 어떤 식으로 하는게 더 편할지 천천히 생각을 해보자..

