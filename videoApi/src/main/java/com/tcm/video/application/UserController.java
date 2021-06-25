package com.tcm.video.application;

import java.util.ArrayList;
import java.util.List;

import com.tcm.video.application.dto.UserDTO;
import com.tcm.video.domain.User;
import com.tcm.video.persistence.UserRepository;

public class UserController {

	public UserDTO createUser(UserDTO userDTO) throws Exception {
		User user = new User(userDTO);
		new UserRepository().saveUser(user);
		return new UserDTO(user);
	}

	public UserDTO removeUser(String id) throws Exception {
		User user = new UserRepository().removeUser(id);
		return new UserDTO(user);
	}

	public List<UserDTO> getAllUsers() throws Exception {
		List<User> users = new UserRepository().getAllUsers();
		return convertToDTO(users);
	}

	private List<UserDTO> convertToDTO(List<User> users) throws Exception {
		List<UserDTO> result = new ArrayList<UserDTO>();
		for (User u : users) {
			result.add(new UserDTO(u));
		}
		return result;

	}

	public UserDTO updateUser(String userId, UserDTO userDTO) throws Exception {
		User user = new UserRepository().getUser(userId);

		user.updateUser(userDTO);

		new UserRepository().updateUser(user);

		return new UserDTO(user);
	}
}
