package comjava.service;

import comjava.dto.AccessTokenDTO;
import comjava.dto.LoginRequestDTO;
import comjava.dto.UserDTO;

public interface UserService {

	AccessTokenDTO login(LoginRequestDTO loginRequestDTO);
	
	UserDTO getUserById(Integer userId);
}
