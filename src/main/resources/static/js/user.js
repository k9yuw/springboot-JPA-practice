let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // ajax 호출 시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // 위에서 만든 data 함수를 JSON 형식으로 변환하는 과정
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType: "json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String으로 오는데, 생긴게 json이라면 => javascript 오브젝트로 변경해줌
        }).done(function(resp){
        // 회원가입 요청이 정상일 때 다음 함수 실행
            alert("회원가입이 완료되었습니다.");
            alert(resp);
            location.href = "/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        // 회원가입 요청에 실패했을 때 다음 함수 실행
        });
    },

        login:function(){
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
            }

            // ajax 호출 시 default가 비동기 호출
            // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!
            $.ajax({
                type: "POST",
                url: "/blog/api/user/login",
                data: JSON.stringify(data), // 위에서 만든 data 함수를 JSON 형식으로 변환하는 과정
                contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
                dataType: "json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String으로 오는데, 생긴게 json이라면 => javascript 오브젝트로 변경해줌
            }).done(function(resp){
            // 회원가입 요청이 정상일 때 다음 함수 실행
                alert("로그인이 완료되었습니다.");
                alert(resp);
                location.href = "/blog";
            }).fail(function(error){
                alert(JSON.stringify(error));
            // 회원가입 요청에 실패했을 때 다음 함수 실행
            });
        }

}

index.init();