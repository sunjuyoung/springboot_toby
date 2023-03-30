package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@UnitTest
@interface FastUnitTest{

}


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})// ANNOTATION_TYPE 다른 에노테이션에 메타에노테이션으로
@Test
@interface UnitTest{

}
public class HelloServiceTest {

    //@UnitTest
   // @FastUnitTest
    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String result = helloService.sayHello("Test");
        assertThat(result).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String res = decorator.sayHello("test");
        assertThat(res).isEqualTo("*test*");
    }




}
