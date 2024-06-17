package org.veenron.sports.permon;

import org.springframework.boot.SpringApplication;

public class TestPerformanceMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.from(PerformanceMonitorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
