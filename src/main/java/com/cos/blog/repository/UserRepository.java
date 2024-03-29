package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.blog.model.User;


//자동으로 bean 등록이 되기 때문에 @Repository를 생략해도 됨
public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA Naming 쿼리 전략
    // SELECT * FROM user WHERE username = ?1 AND password = ?2; 를 자동으로 실행
    User findByUsernameAndPassword(String username, String password);

    //또 다른 방법
    //@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
    //User login(String username, String password);
}
