package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

    @Autowired // DummyControllerTest가 메모리에 뜨면, 자동으로 같이 메모리에 뜨게 해줌. 이걸 DI(의존성 주입)이라고 함!
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        return "회원가입이 완료되었습니다.";
    }
}
