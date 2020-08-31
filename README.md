## springboot_practice

&nbsp;본 프로젝트는 공부 목적으로 스프링부트를 연습하는 프로젝트입니다.

[목표]
+ SpringBoot, JUnit4, JPA, Spring Security
+ AWS EC2, S3, RDS, Travis CI 

***

[개발 환경]
+ JAVA 1.8 (Open JDK, Zulu)
+ Spring Boot (2.3.3)
+ Maven (책은 Gradle로 되어있지만, 익숙한 Maven을 사용해보기로 하였습니다.)
+ IntelliJ Community
+ H2 Database (1.4.197)

***

[참고 자료]
+ 이동욱님 저, 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 [(YES24 바로가기)](http://www.yes24.com/Product/Goods/83849117 "YES24 바로가기")
+ 백기선님 인프런 강의, 스프링 부트 개념과 활용 [(인프런 바로가기)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8 "인프런 바로가기")
+ 구글

***

[참고 사항]
+ H2 Database는 1.4.197 버전을 권장합니다. [(참조 블로그)](https://slf4me.com/post/spring-boot/mybatis-h2-problem/ "참조 블로그")
+ H2 Console에서 JDBC URL은 실행될 때마다 변경이 됩니다. 이 URL 을 입력하고 연결해야 정상적으로 테이블확인이 가능합니다.
  + ex) H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:2a761096-0811-42a1-a556-9e85eca4c612'
