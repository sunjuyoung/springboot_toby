package com.example.helloboot;



import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		//스프링컨테이너 사용
		//애플리케이션 컨텍스중 코드에 의해 쉽게 만들수 있는 객체
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);//빈등록,어떤 객체보단 클래스 메타정보를 넣어준다, getBean으로 반환
		applicationContext.registerBean(SimpleHelloService.class);//빈 등록 순서는 컨테이너가 자동으로 처리한다
		applicationContext.refresh();//컨테이너 초기화


		//HelloController helloController = new HelloController();
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			//서블릿등록
			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//인증, 보안, 다국어, 공동 기능...

					//매핑
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class);
						String result = helloController.hello(name);//바인딩
//
//						resp.setStatus(HttpStatus.OK.value());
//						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(result);
					} else if (req.getRequestURI().equals("/user")) {

					}else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*");//front-controller 역활
		});
		
		webServer.start();//톰캣 서브릿 컨테이너 실행
	}

}
