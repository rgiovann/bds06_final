package com.devsuperior.movieflix.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.RoleDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
 	@Autowired
 	private ModelMapper modelMapper;
	
//	@Autowired
//	private AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 		User user = userRepository.findByEmail(username);
 
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("User not found: " + username);
		}
		logger.info("User found: " + username);
		return user;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		 		
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Error. Id not found: " + id));
		return EntityToDTO(entity);
	}
	
	public UserDTO EntityToDTO(User entity) {
		UserDTO userDTO = modelMapper.map(entity, UserDTO.class);
		Set<RoleDTO> roleDTOSet = entity.getRoles().stream().map(element -> modelMapper.map(element, RoleDTO.class))
				.collect(Collectors.toSet());
		userDTO.getRoles().clear();
		for (RoleDTO roleItem : roleDTOSet) {
			userDTO.getRoles().add(roleItem);
		}
		return userDTO;
	}

}
