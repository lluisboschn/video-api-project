package com.tcm.video.persistence;

import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tcm.video.domain.User;
import com.tcm.video.domain.Video;

public class UserRepository {

	public User getUser(String id) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();

		try {
			String sql = "SELECT * FROM USERS WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();

			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				User user = createUserFromBBDD(rs);
				user.addVideos(new VideoRepository().getAllVideosByUserId(id));
				return user;
			}

			throw new Exception();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
	}

	private User createUserFromBBDD(ResultSet rs) throws Exception {
		String id = rs.getString("ID");
		String email = rs.getString("EMAIL");
		String password = rs.getString("PASSWORD");

		return new User(id, email, password);
	}

	public void saveUser(User user) throws Exception {
		if (user == null)
			throw new Exception("");

		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "Insert into USERS(ID, EMAIL, PASSWORD) values (?, ?, ?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, user.getId());
		pst.setString(2, user.getEmail());
		pst.setString(3, user.getPassword());

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}

		for (Video video : user.getVideos()) {
			new VideoRepository().saveVideo(user, video);
		}

	}

	public User removeUser(String id) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();

		User user = getUser(id);

		new VideoRepository().deleteVideos(id);

		String sql = "DELETE FROM USERS WHERE ID=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, user.getId());

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}

		return user;
	}

	public List<User> getAllUsers() throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();

		try {
			String sql = "SELECT * FROM USERS";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();

			ResultSet rs = preparedStatement.executeQuery();

			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = createUserFromBBDD(rs);
				user.addVideos(new VideoRepository().getAllVideosByUserId(user.getId()));
				users.add(user);

			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
	}

	public void updateUser(User user) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "UPDATE USERS SET EMAIL=?, PASSWORD=? WHERE ID=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, user.getEmail());
		pst.setString(2, user.getPassword());
		pst.setString(3, user.getId());

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}
	}
}
