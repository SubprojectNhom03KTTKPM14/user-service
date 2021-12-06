package comjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comjava.dto.UserDTO;
import comjava.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public UserDTO getById(@PathVariable("userId") Integer userId) {

		return userService.getUserById(userId);
	}
	
	@GetMapping("/me")
	public UserDTO getMe(@RequestHeader("userId") Integer userId) {

		return userService.getUserById(userId);
	}

	@GetMapping("")
	public List<UserDTO> getUserList() {

		return userService.getUserList();
	}
}
