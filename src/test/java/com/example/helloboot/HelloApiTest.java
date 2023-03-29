package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {

    @Test
    void helloApi(){
        //http localhost:8080/hello?name=Spring

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> result =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");


        //status 200
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //header(Content-type text/plain
        //text/plain;charset=ISO-8859-1"                                                //expected: "text/plain"
        assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        //body Hello Spring
        assertThat(result.getBody()).isEqualTo("Hello Spring");

        //수행속도

    }

    @Test
    void helloApiFailed(){
        //http localhost:8080/hello?name=Spring

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> result =
                rest.getForEntity("http://localhost:8080/hello?name=", String.class);


        //status
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
