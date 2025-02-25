package com.booksystem.book_social_network;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.booksystem.book_social_network.user.role.Role;
import com.booksystem.book_social_network.user.role.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
public class BookSocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSocialNetworkApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepo) {
		return args -> {
			if (!roleRepo.getByName("USER").isPresent()) {
				roleRepo.save(new Role("USER"));
			}
		};
	}

}
