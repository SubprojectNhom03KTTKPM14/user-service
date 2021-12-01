package comjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comjava.dto.AccessTokenDTO;
import comjava.dto.LoginRequestDTO;
import comjava.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public AccessTokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

		return userService.login(loginRequestDTO);
	}
}
