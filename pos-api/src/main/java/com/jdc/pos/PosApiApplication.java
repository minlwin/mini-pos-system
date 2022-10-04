package com.jdc.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
	"classpath:token.properties",
	"classpath:users.properties"	
})
public class PosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApiApplication.class, args);
	}

}
