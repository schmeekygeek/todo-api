package io.schmeekydev.todoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1/")
public class TODOApplication {

	public static void main(String[] args) {
		SpringApplication.run(TODOApplication.class, args);
	}

    @GetMapping(path = "/hello")
    public String hello(){
        return "<h1>Hello World</h1>";
    }

}
