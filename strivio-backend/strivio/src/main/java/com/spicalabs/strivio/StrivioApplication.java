package com.spicalabs.strivio;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spicalabs.strivio.model.StrivioUser;
import com.spicalabs.strivio.repo.UserRepo;

@SpringBootApplication
public class StrivioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrivioApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDatabase (UserRepo userRepo){
		return args -> {
			UUID testUserId = UUID.fromString("d3b07384-d113-4c4e-9c8e-cf03ef7687d0");

			if(!userRepo.existsById(testUserId)){
				StrivioUser mockUser = new StrivioUser();

                mockUser.setId(testUserId);
                mockUser.setUsername("taaha_runner");
				mockUser.setPhoneNumber("7887455945");
                userRepo.save(mockUser);
                System.out.println("✅ Mock runner user seeded successfully into database!");
			}
		};
	}

}
