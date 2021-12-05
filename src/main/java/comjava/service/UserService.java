package comjava.service;

import java.util.List;

import comjava.dto.AccessTokenDTO;
import comjava.dto.LoginRequestDTO;
import comjava.dto.UserDTO;
import comjava.entity.User;

public interface UserService {

	AccessTokenDTO login(LoginRequestDTO loginRequestDTO);

	AccessTokenDTO register(UserDTO userDTO);

	UserDTO getUserById(Integer userId);

	List<UserDTO> getUserList();

}
