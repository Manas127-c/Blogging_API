package com.blogging.spring.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blogging.spring.config.AppConstsnts;
import com.blogging.spring.entities.Role;
import com.blogging.spring.entities.User;
import com.blogging.spring.exception.ResourceNotFoundException;
import com.blogging.spring.payload.UserDTO;
import com.blogging.spring.repository.RoleRepository;
import com.blogging.spring.repository.UserRepository;
import com.blogging.spring.services.UserService;

@Component
@Service
public class UserServiceImplemet implements UserService {
	
	@Autowired
	UserRepository uRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDTO createUser(UserDTO userdto) {
		
		User user=this.userDTOtoUser(userdto);// first it get data dto to entity class
		User savedUser=	uRepository.save(user);//then perform operation and save data in saveduser
		return this.userToUserDTO(savedUser);// and then get data saved to dto and return it because 
		//return type is UserDto class
	}

	@Override
	public UserDTO updateUser(UserDTO udto, Integer id) {
		
		User user=uRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
		
		user.setAbout(udto.getAbout());
		user.setEmail(udto.getEmail());
		user.setName(udto.getName());
		user.setPassword(udto.getPassword());
		
		User updateUser= uRepository.save(user);
		
		return this.userToUserDTO(updateUser);
	}

	@Override
	public void deleteUser(Integer id) {
		User user=uRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
		
		uRepository.delete(user);
	}

	@Override
	public UserDTO getUser(Integer id) {
		
		User user=uRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
		
		return this.userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users=uRepository.findAll();
		List<UserDTO> uDtos=users.stream().map(user-> this.userToUserDTO(user)).collect(Collectors.toList());
		return uDtos;
	}
	
	public User userDTOtoUser(UserDTO udto) {
		
		//it maps all data of a model object to another model object without getter setter
		User user=this.modelMapper.map(udto, User.class);
//		user.setId(udto.getId());
//		user.setAbout(udto.getAbout());
//		user.setEmail(udto.getEmail());
//		user.setName(udto.getName());
//		user.setPassword(udto.getPassword());
		return user;
	}
	
	public UserDTO userToUserDTO(User user) {
		UserDTO udto=this.modelMapper.map(user, UserDTO.class);
		
//		udto.setId(user.getId());
//		udto.setAbout(user.getAbout());
//		udto.setEmail(user.getEmail());
//		udto.setName(user.getName());
//		udto.setPassword(user.getPassword());
		return udto;
	}

	@Override
	public UserDTO registerUser(UserDTO userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
		//Encode password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//assign roles
		Role role=this.roleRepository.findById(AppConstsnts.ROLE_ID).get();
		user.getRoles().add(role);
		
		//register new user
		User newUser=this.uRepository.save(user);
		
		return this.modelMapper.map(newUser, UserDTO.class);
	}
}
