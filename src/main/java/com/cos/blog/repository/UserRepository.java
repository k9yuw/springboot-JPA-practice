package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.blog.model.User;


//자동으로 bean 등록이 되기 때문에 @Repository를 생략해도 됨
public interface UserRepository extends JpaRepository<User, Integer> {
}
