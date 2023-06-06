package com.oldandsea.pcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PcbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcbApplication.class, args);
	}

}
