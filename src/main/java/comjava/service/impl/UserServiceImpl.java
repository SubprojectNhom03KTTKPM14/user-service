package comjava.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comjava.dto.AccessTokenDTO;
import comjava.dto.LoginRequestDTO;
import comjava.dto.UserDTO;
import comjava.entity.RoleType;
import comjava.entity.User;
import comjava.repository.UserRepository;
import comjava.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public AccessTokenDTO login(LoginRequestDTO loginRequestDTO) {

		User user = userRepository.findByEmail(loginRequestDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword()))
			throw new RuntimeException("Password wrong");

		String token = jwtTokenProvider.generateToken(user.getId());
		return new AccessTokenDTO(token);
	}

	@Override
	public UserDTO getUserById(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		UserDTO userDTO = new UserDTO(user);
		return userDTO;
	}

	@Override
	public AccessTokenDTO register(UserDTO userDTO) {
		
		String email = userDTO.getEmail();
		
		if(userRepository.findByEmail(email).isPresent())
			throw new RuntimeException("User is already exist");
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		int userId = userRepository.save(toUser(userDTO)).getId();
		String token = jwtTokenProvider.generateToken(userId);
		
		return new AccessTokenDTO(token);
	}

	@Override
	public List<UserDTO> getUserList() {
		return userRepository.findAll().stream()
				.map(userEle -> new UserDTO(userEle))
				.collect(Collectors.toList());
	}
	
	
	public User toUser(UserDTO userDTO) {
		
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setAddress(user.getAddress());
		user.setRoleType(RoleType.USER);
		return user;
	}
}
