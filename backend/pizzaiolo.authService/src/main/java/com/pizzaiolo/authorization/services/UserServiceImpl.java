package com.pizzaiolo.authorization.services;

import static com.pizzaiolo.authorization.utils.Constants.USER_NOT_FOUND_MESSAGE;

import com.pizzaiolo.authorization.repositories.UserRepository;
import com.pizzaiolo.authorization.services.interfaces.UserService;
import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.pizzaiolo.authorization.models.dtos.CreateUserDto;
import com.pizzaiolo.authorization.models.dtos.UpdatePasswordDto;
import com.pizzaiolo.authorization.models.dtos.UpdateRolDto;
import com.pizzaiolo.authorization.models.dtos.UpdateUserDto;
import com.pizzaiolo.authorization.models.entities.User;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder; // Fails when injected by the constructor

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(CreateUserDto createUserDto) {
		User newUser = new User();

		newUser.setEmail(createUserDto.getEmail()).setFirstName(createUserDto.getFirstName())
				.setLastName(createUserDto.getLastName()).setPassword(bCryptEncoder.encode(createUserDto.getPassword()))
				.setGender(createUserDto.getGender()).setConfirmed(createUserDto.isConfirmed())
				.setEnabled(createUserDto.isEnabled()).setAvatar(null).setTimezone(createUserDto.getTimezone())
				.setCoordinates(createUserDto.getCoordinates()).setRole(createUserDto.getRole());

		return userRepository.save(newUser);
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);

		return list;
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(new ObjectId(id));
	}

	@Override
	public User findByEmail(String email) throws ResourceNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE);
		}

		return optionalUser.get();
	}

	@Override
	public User findById(String id) throws ResourceNotFoundException {
		Optional<User> optionalUser = userRepository.findById(new ObjectId(id));

		if (optionalUser.isEmpty()) {
			throw new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE);
		}

		return optionalUser.get();
	}

	@Override
	public User update(String id, UpdateUserDto updateUserDto) throws ResourceNotFoundException {
		User user = findById(id);

		if (updateUserDto.getFirstName() != null) {
			user.setFirstName(updateUserDto.getFirstName());
		}
		if (updateUserDto.getLastName() != null) {
			user.setLastName(updateUserDto.getLastName());
		}
		if (updateUserDto.getTimezone() != null) {
			user.setTimezone(updateUserDto.getTimezone());
		}
		if (updateUserDto.getGender() != null) {
			user.setGender(updateUserDto.getGender());
		}
		if (updateUserDto.getAvatar() != null) {
			user.setAvatar(updateUserDto.getAvatar());
		}
		if (updateUserDto.getCoordinates() != null) {
			user.setCoordinates(updateUserDto.getCoordinates());
		}
		if (updateUserDto.getPhone() != null) {
			user.setPhone(updateUserDto.getPhone());
		}

		return userRepository.save(user);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public User updatePassword(String id, UpdatePasswordDto updatePasswordDto) throws ResourceNotFoundException {
		User user = findById(id);

		if (bCryptEncoder.matches(updatePasswordDto.getCurrentPassword(), user.getPassword())) {
			user.setPassword(bCryptEncoder.encode(updatePasswordDto.getNewPassword()));

			return userRepository.save(user);
		}

		return null;
	}

	@Override
	public void updatePassword(String id, String newPassword) throws ResourceNotFoundException {
		User user = findById(id);

		user.setPassword(bCryptEncoder.encode(newPassword));

		userRepository.save(user);
	}

	@Override
	public User updateRol(String id, UpdateRolDto updateRolDto) throws ResourceNotFoundException {
		User user = findById(id);
		
		user.setRole(updateRolDto.getNewRole());

		return userRepository.save(user);
	}

//	@Override
//	public void updateRol(String id, Role newRole) throws ResourceNotFoundException {
//		User user = findById(id);
//
//		user.setRole(newRole);
//
//		userRepository.save(user);
//	}
	

	public void confirm(String id) throws ResourceNotFoundException {
		User user = findById(id);

		user.setConfirmed(true);

		userRepository.save(user);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(username);

		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		User user = userOptional.get();

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnabled(), true, true, user.isConfirmed(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

		user.allPermissions().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

		return authorities;
	}

	@Override
	public Boolean existsByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) 
			return false;
		return true;
	}
	
	
}
