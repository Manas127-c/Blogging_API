package com.blogging.spring;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogging.spring.config.AppConstsnts;
import com.blogging.spring.entities.Role;
import com.blogging.spring.repository.RoleRepository;

@SpringBootApplication
public class BloggingSpringBootAppApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder pEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingSpringBootAppApplication.class, args);
	}
	
	//create a model mapper bean object
	
	@Bean
	public ModelMapper mapmodel() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.pEncoder.encode("John1010"));
		
		try {
			Role role1=new Role();
			role1.setrId(AppConstsnts.ADMIN_ID);
			role1.setrName("ROLE_ADMIN");
			Role role2=new Role();
			role2.setrId(AppConstsnts.ROLE_ID);
			role2.setrName("ROLE_NORMAL");
			List<Role> roles=List.of(role1, role2);
			this.roleRepository.saveAll(roles);
		} catch (Exception e) {
			e.toString();
		}
	}
}
