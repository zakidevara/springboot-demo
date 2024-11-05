package com.devara.springboot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		var applicationModules = ApplicationModules.of(Application.class);
		applicationModules.verify();
		applicationModules.forEach(System.out::println);
	}

}
