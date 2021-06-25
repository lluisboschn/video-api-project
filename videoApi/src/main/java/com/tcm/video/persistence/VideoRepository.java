package com.tcm.video.persistence;

import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tcm.video.domain.User;
import com.tcm.video.domain.Video;

public class VideoRepository {

	public List<Video> getAllVideosByUserId(String userId) throws Exception {

		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "SELECT * FROM VIDEOS WHERE USER_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.clearParameters();

		preparedStatement.setString(1, userId);
		ResultSet rs = preparedStatement.executeQuery();

		List<Video> videos = new ArrayList<Video>();
		while (rs.next()) {
			videos.add(createVideoFromBBDD(rs));
		}
		return videos;
	}

	private Video createVideoFromBBDD(ResultSet rs) throws Exception {
		String id = rs.getString("ID");
		String name = rs.getString("NAME");
		String url = rs.getString("URL");

		return new Video(id, name, url);
	}

	public void deleteVideos(String userId) throws Exception {

		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "DELETE FROM VIDEOS WHERE USER_ID=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, userId);

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}

	}

	public void updateVideo(Video video) throws Exception {

		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "UPDATE VIDEOS SET NAME=?, URL=? WHERE ID=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, video.getName());
		pst.setString(2, video.getUrl());
		pst.setString(3, video.getId());

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}
	}

	public void saveVideo(User user, Video video) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();

		String sql = "Insert into VIDEOS(ID, NAME, URL, USER_ID) values (?, ?, ?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, video.getId());
		pst.setString(2, video.getName());
		pst.setString(3, video.getUrl());
		pst.setString(4, user.getId());

		if (pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}
	}
}
