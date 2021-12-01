package comjava.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comjava.dto.AccessTokenDTO;
import comjava.dto.LoginRequestDTO;
import comjava.dto.UserDTO;
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

		if (!passwordEncoder.matches(loginRequestDTO.getPassword(), loginRequestDTO.getPassword()))
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
}
