package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에서 사용되는 방식인 auto_increment를 따름
    private int id;

    @Column(nullable=false, length=100)
    private String title;

    @Lob // 대용량 데이터를 처리할 때 씀
    private String content; // 섬머노트라는 라이브러리 -> html 태그가 섞여 디자인되므로 용량이 커짐

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // 보드가 many, 유저가 one이 되어 '한 명의 유저가 여러 개의 게시글을 쓸 수 있다' 라는 연관관계를 맺어줌
    @JoinColumn(name="userId")
    private User user; // DB는 오브젝트를 저장할 수 없어서 FK를 쓰는데, 자바는 오브젝트를 저장할 수 있다. JPA ORM 사용 시 이 충돌 해결 가능

    @CreationTimestamp
    private Timestamp createDate;
}
