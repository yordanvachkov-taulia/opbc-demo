package com.example.yorvac

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableProcessApplication('opbc-demo')
class OpbcDemoApplication {

	static void main(String[] args) {
		SpringApplication.run(OpbcDemoApplication, args)
	}

}
