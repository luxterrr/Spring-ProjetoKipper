package com.kaizen.ProjetoKipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class KipperApplication {

	public static void main(String[] args) {
		SpringApplication.run(KipperApplication.class, args);
	}

}
