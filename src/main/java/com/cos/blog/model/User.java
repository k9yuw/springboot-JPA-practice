package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity // class를 테이블화
@Data // getter setter 만들어줌
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ColumnDefault(" 'user' ")
    private String role; //나중에는 String 대신 Enum을 쓰는게 좋음 -> 데이터에 대한 도메인을 만들어줄 수 있음.

    @CreationTimestamp // 시간이 자동으로 입력됨!
    private Timestamp createDate;

}
