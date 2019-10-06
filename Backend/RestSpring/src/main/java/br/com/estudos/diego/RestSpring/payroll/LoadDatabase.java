package br.com.estudos.diego.RestSpring.payroll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			repository.save(new Employee("Diego", "P.O."));
			repository.save(new Employee("Diego", "Dev"));
		};
	}

}
