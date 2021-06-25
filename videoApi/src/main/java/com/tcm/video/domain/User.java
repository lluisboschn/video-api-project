package com.tcm.video.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tcm.video.application.dto.UserDTO;

public class User {

	private String id;
	private String email;
	private String password;

	private List<Video> videos = new ArrayList<>();

	public User(UserDTO userDTO) throws Exception {
		if (userDTO == null)
			throw new Exception();

		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.id = UUID.randomUUID().toString();
	}

	public User(String id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;

	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void addVideo(Video video) throws Exception {
		if (video == null)
			throw new Exception();

		this.videos.add(video);
	}

	public void addVideos(List<Video> videos) throws Exception {
		if (videos == null)
			throw new Exception();

		for (Video video : videos) {
			addVideo(video);
		}
	}

	public void removeAllVideos() {
		this.videos = new ArrayList<Video>();
	}

	public Video getVideoById(String id) {
		return this.videos.stream().filter(x -> x.getId().equals(id)).findAny().get();
	}

	public Video removeVideoById(String id) throws Exception {
		for (Video v : new ArrayList<>(videos)) {
			if (v.getId().equals(id)) {
				videos.remove(v);
				return v;
			}
		}
		throw new Exception("Not found");
	}

	public String getPassword() {
		return this.password;
	}

	public void updateUser(UserDTO userDTO) throws Exception {
		if (userDTO == null)
			throw new Exception();

		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
	}
}
