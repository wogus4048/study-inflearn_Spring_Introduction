package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트어플리케이션 어노테이션
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		// 스프링어플리케이션 클래스를 매개변수로 받는 해당 명령어를 실행하여 스프링부트를 어플리케이선을 실행한다.

	}

}
