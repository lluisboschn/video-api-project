package com.tcm.video.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcm.video.application.UserController;
import com.tcm.video.application.dto.UserDTO;

@RestController
public class UserRestController {

	@PostMapping("/users")
	public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
		return new UserController().createUser(userDTO);
	}

	@GetMapping("/users")
	public List<UserDTO> getAllUsers() throws Exception {
		return new UserController().getAllUsers();
	}

	@DeleteMapping("/users/{userId}")
	public UserDTO deleteUser(@PathVariable String userId) throws Exception {
		return new UserController().removeUser(userId);
	}

	@PutMapping("/users/{userId}")
	public UserDTO updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) throws Exception {
		return new UserController().updateUser(userId, userDTO);
	}

}
