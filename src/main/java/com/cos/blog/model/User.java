package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


@Entity // class를 테이블화
@Data // getter setter 만들어줌
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // insert 할 때 null인 필드는 알아서 제외시켜줌
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity란 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 것
    private int id; //mySQL에서 auto_increment로 넘버링 할 것

    @Column(nullable = false, length = 30)
    private String username; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;
    
    // @ColumDefault("user") -> 원래 했던 방식
    @Enumerated(EnumType.STRING) // DB에는 RoleType이라는게 없기 때문에 Role의 타입이 String임을 알려줘야 됨
    private RoleType role; //String 대신 Enum을 쓰는게 좋음 -> 데이터에 대한 도메인을 만들어줄 수 있음. (정해진 값만 쓸 수 있도록 해서 내가 넣는 값들을 강제. 실수 방지.)

    @CreationTimestamp // 시간이 자동으로 입력됨!
    private Timestamp createDate;

}
