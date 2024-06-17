package org.veenron.sports.permon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@Modulith(systemName = "PerformanceMonitor")
public class PerformanceMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceMonitorApplication.class, args);
	}

}
