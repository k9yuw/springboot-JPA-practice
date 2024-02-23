package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import jakarta.persistence.GeneratedValue;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import javax.swing.*;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // DummyControllerTest가 메모리에 뜨면, 자동으로 같이 메모리에 뜨게 해줌. 이걸 DI(의존성 주입)이라고 함.
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            return "해당 id가 DB에 없으므로 삭제에 실패하였습니다."
        }

        return "삭제되었습니다. id: " + id;
    }

    // 이메일, 패스워드 수정
    @Transactional // 이걸 걸면 save 안해도 업데이트가 됨. 이걸 더티체킹이라고 함. 이는 함수 종료 시 자동 커밋하여 변경된 점을 자동으로 영속성 컨텍스트에서 DB로 flush
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){ // @RequestBody 어노테이션은 json 데이터를 요청 시 이를 MessageConverter의 Jackson 라이브러리가 java object 형태로 변환하여 받아줌.
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> { // 람다식
            return new IllegalArgumentException("수정에 실패하였습니다.");
        }); // requestUser의 아이디가 DB에 존재하는지 먼저 확인. 이러면 null 값의 id를 받아오는 경우가 없을 것

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // save 함수는 id를 전달하지 않으면 insert를 해주고,
        // id를 전달했을 때 1) 해당 id에 대한 데이터가 있으면 update 2) 해당 id에 대한 데이터가 없으면 insert
        // id가 있는지 없는지 영속성 컨텍스트에서 DB에 조회해서 알아서 확인해줌.
        // userRepository.save(requestUser);

        // 더티체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){ //List<User>: 이 메소드가 User 객체 리스트를 반환. list()는 메소드의 이름
        return userRepository.findAll(); //findAll은 JpaRepository 인터페이스에 정의된 메소드로, DB에 저장된 모든 User 객체들을 조회하여 반환.
    }

    // 한 페이지당 2건의 데이터를 리턴받을 예정
    // page, pageable은 JPA에서 페이징 처리를 위해 제공하는 인터페이스. (27강 필기 참조)
    @GetMapping("/dummy/user/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id",direction= Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent(); // 결과는 리스트로 받아오는게 좋다.
        return users;
    }

    @GetMapping("/dummy/user/{id}") ///// 26강 다시보기
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
            @Override
            public IllegalArgumentException get() {
                //TODO Auto-generated method stub
                return new IllegalArgumentException("해당 유저는 없습니다.");
            }
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
