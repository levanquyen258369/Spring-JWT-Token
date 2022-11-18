package io.gatearrays.userservice;

import io.gatearrays.userservice.domain.Role;
import io.gatearrays.userservice.domain.User;
import io.gatearrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null,"John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null,"Sam Smith", "sam", "1234", new ArrayList<>()));
			userService.saveUser(new User(null,"Tom Terry", "tom", "1234", new ArrayList<>()));
			userService.saveUser(new User(null,"Katy Perry", "katy", "1234", new ArrayList<>()));

			//Phân quyền
			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("sam", "ROLE_MANAGER");
			userService.addRoleToUser("tom", "ROLE_ADMIN");
			userService.addRoleToUser("katy", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("katy", "ROLE_ADMIN");
			userService.addRoleToUser("katy", "ROLE_USER");
		};
	}

}
