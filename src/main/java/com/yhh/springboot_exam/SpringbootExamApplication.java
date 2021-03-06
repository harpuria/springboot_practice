package com.yhh.springboot_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화 (이걸 삭제하고 JpaConfig 에 붙일것임)
@SpringBootApplication // SpringBootApplication 안에 컴포넌트 스캔과 자동설정 등 설정이 미리 되어 있다.
public class SpringbootExamApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootExamApplication.class, args);

		// 위(디폴트)보다 아래처럼 하는 것을 권장. why? 좀 더 세부적인 설정을 할 수 있기 때문에.
		SpringApplication app = new SpringApplication(SpringbootExamApplication.class);
		app.run();
	}

}
