package com.cos.blog.test;


import org.springframework.web.bind.annotation.*;

//사용자의 요청에 대해 data로 응답하는 Controller
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG+"getter: " + m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter: " + m.getId());
        return "lombok test 완료";
    }

    //인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다!!
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청 : " + m.getId() +", " + m.getUsername() +", " + m.getPassword() +", " +  m.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청 : " + m.getId() +", " + m.getUsername() +", " + m.getPassword() +", " +  m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
