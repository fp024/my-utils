# vault-util

> 💡 스프링 6.x, 스프링부트 3.x 호환 버전
>
> * `3.0.0-SNAPSHOT` 버전은 최신 버전 호환용으로 사용하기로함..
>
> 토큰 방식으로 아주 간단하게 vault를 읽기만 하는 모듈인데.. 
>
> 차차 개선해나아가자 ~ 😅

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
    // mavenLocal을 사용할 때는 content 필터링을 해줘야한다.
    mavenLocal {    
      content {
        includeGroup "org.fp024.util"
      }
    }
    mavenCentral()
  }
  ...
  
  dependencies {
    ... // 모듈의 디펜던시 추가
    implementation 'org.fp024.util:vault-util:3.0.0-SNAPSHOT'
    // implementation "org.fp024.util:vault-util:${vaultUtilVersion}"
    ...
  }
  ```

  * `mavelLocal()`을 사용할 때는 필요한 커스텀 라이브러리만 포함되도록 필터링을 해주는 것이 좋음

    * querydsl-apt의 classifier 관련해서 문제를 겪어서 검색을 해보고 알게되었다. 😅
    * https://github.com/gradle/gradle/issues/18276#issuecomment-921628988

    

* **테스트를 위해서 vault 서버에 값을 미리 넣어놨다.**

  ![image-20230811204716376](doc-resources/image-20230811204716376.png)

  



## 어쩌다 보니.. `spring-cloud-starter-vault-config` 를 사용하게 되었는데...

Spring Boot 환경에서는 이거 사용하는게 편하겠다. 이걸 사용하면 application.yml에 설정할 값들을 vault 서버에 저장해두고 바로 치환해서 사용할 수 가 있다.

`스프링 시큐리티 인 액션` 책의 12장을 보다가 ... 저자님 예제를 보니 깃허브의 클라이언트 ID와 클라이언트 비밀번호 평문이 `application.properties`에 평문으로 입력되어 있어서 그걸 vault 서버에 저장해서 사용해보고 싶었는데..

내 유틸리티 클래스로는 `application.yml`에 고정으로 적힌 값의 치환은 불가능해서, `spring-cloud-starter-vault-config`를 사용해 봤는데... 아주 원할하게 잘 되었다.

```yml
# application.yml
spring:
  config:
    import: vault://
  cloud:
    vault:
      uri: http://lvm.vault-server:8200
      kv:
        backend: kv_study_project
        default-context: spring_security_in_action
      # token: 여기에 설정하지 않았을 경우.
      # ~/.vault-token 경로에 토큰 파일이 저장되어 있어야함.
      # Windows 환경이라면 %USERPROFILE%/.vault-token 파일에 저장해야함.
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: ${github_client_id}
            client-secret: ${github_client_secret}
```

* `${github_client_id}`, `${github_client_secret}`가 vault 서버에 저장된 값으로 알아서 치환된다.
* 토큰은 원래는 별도의 properties 파일을 `spring.config.additional-location` 설정으로 처리를 하고 싶긴했는데, 어떻게 해도 로드가 안되서.. 토큰 로드 실패 예외로그 메시지에서 알려줬던 방식 중 하나인 ... 사용자 홈 경로에 `.vault-token` 파일을 두고 실행하니 잘 동작 하였다.

#### 결국 ... spring-cloud-starter-vault-config의 사용법을 잘 아는게 더 나을 것 같음. 😅😅😅

