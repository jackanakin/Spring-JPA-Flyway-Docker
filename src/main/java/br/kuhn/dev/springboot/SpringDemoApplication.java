package br.kuhn.dev.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
