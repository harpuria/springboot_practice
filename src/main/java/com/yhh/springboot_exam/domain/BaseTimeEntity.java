package com.yhh.springboot_exam.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 모든 JPA Entity 의 부모클래스가 되게 한다. 이 클래스를 상속하면 필드들을 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // Auditing 기능
public class BaseTimeEntity {
    // 생성될 때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 갱신될 대 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
