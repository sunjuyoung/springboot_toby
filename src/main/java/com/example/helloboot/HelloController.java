package com.example.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    //applicationContext.registerBean(SimpleHelloService.class) 컨테이너에 SimpleHelloService 빈으로 등록되어있다
    //스프링컨테이너가 생성자 주입으로 HelloService ,SimpleHelloService 자동 매핑시켜준다.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping
    @ResponseBody
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
