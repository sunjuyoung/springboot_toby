package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloControllerTest {

    @Test
    void test(){
       HelloController helloController = new HelloController(name -> name);

       String re = helloController.hello("Test");
       assertThat(re).isEqualTo("Hello Test");
    }

    @Test
    void test1(){

        HelloController helloController = new HelloController(name -> name);

//        assertThatThrownBy(()->{
//            helloController.hello(null);
//        }).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(()->{
             helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);

    }
}
