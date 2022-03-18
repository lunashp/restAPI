package kr.co.dajsoft.hell0.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;


//    /* 해당 엔티티를 저장하기 이전에 실행 */
//    @PrePersist
//    public void onPrePersist(){
//        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//        this.modDate = this.regDate;
//    }
//    /* 해당 엔티티를 업데이트 하기 이전에 실행*/
//    @PreUpdate
//    public void onPreUpdate(){
//        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//    }

}
