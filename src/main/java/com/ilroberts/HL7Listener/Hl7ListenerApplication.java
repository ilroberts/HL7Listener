package com.ilroberts.HL7Listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.ilroberts.HL7Listener")
@ImportResource("classpath:camel-context.xml")
public class Hl7ListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hl7ListenerApplication.class, args);
	}
}
