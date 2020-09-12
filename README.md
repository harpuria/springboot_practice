## springboot_practice

본 프로젝트는 공부 목적으로 스프링부트를 연습하는 프로젝트입니다.
[(사이트 바로가기)](http://ec2-13-209-152-211.ap-northeast-2.compute.amazonaws.com "사이트 바로가기")

***

[목표]
+ 아래 제반 기술에 대한 기본적인 이해 및 실습
    + SpringBoot, JUnit4, JPA, Spring Security, Mustache
    + AWS EC2, S3, RDS, Travis CI, NginX

***

[개발 환경]
+ JAVA (1.8, Zulu Open JDK)
+ Spring Boot (2.3.3)
+ Mustache (2.3.3)
+ Maven (3.6.3)
+ IntelliJ Community (2020.2.1)
+ H2 Database (1.4.197)
+ Maria DB (10.4.8)

***

[참고 자료]
+ 이동욱님 저, 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 [(YES24 바로가기)](http://www.yes24.com/Product/Goods/83849117 "YES24 바로가기")
+ 백기선님 인프런 강의, 스프링 부트 개념과 활용 [(인프런 바로가기)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8 "인프런 바로가기")
+ 구글

***

[실습 참고 사항]
+ H2 Database는 1.4.197 버전을 권장합니다. [(참조 블로그)](https://slf4me.com/post/spring-boot/mybatis-h2-problem/ "참조 블로그")
+ H2 Console에서 JDBC URL은 실행될 때마다 변경이 됩니다. 이 URL 을 입력하고 연결해야 정상적으로 테이블확인이 가능합니다.
  + ex) H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:2a761096-0811-42a1-a556-9e85eca4c612'
  + 혹은 application.properties에 spring.datasource.url의 값에 'jdbc:h2:mem:CUSTOM_URL_NAME' 을 입력한 뒤 이 URL 로 연결을 해주시면 됩니다.
+ MediaType.APPLICATION_JSON_UTF8 은 5.2 이후부터 deprecated 되었습니다.
  + MediaType.APPLICATION_JSON 로 대체하시면 됩니다.
+ AWS 계정 가입 (프리티어 2021. 09. 03 까지)
  + nuberus@naver.com / harpuria87@gmail.com (프리티어 종료)
+ p.100 application.properties / p.309 application-real.properties [(이슈 확인)](https://github.com/jojoldu/freelec-springboot2-webservice/issues/158 "이슈 확인")
  + MySQL5InnoDBDialect Deprecated 되어서 MySQL55Dialect 로 변경하였다.(이거 기본 엔진이 InnoDB)
+ 리눅스에서 tail -f nohup.out 으로 실시간 로그 확인 가능.
+ Travis CI Maven 설정 관련 참고 블로그 [(참조 블로그)](https://velog.io/@junwoo4690/Travis-CI%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-Maven-SpringBoot%EC%97%90-CI-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0 "Travis CI Maven 설정")