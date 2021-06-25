package com.tcm.video.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.tcm.video.domain.User;
import com.tcm.video.domain.Video;

public class UserDTO {

	private String id;
	private String email;
	private String password;
	private List<VideoDTO> videos=new ArrayList<>();
	
	public UserDTO() {
		
	}
	public UserDTO(User user) throws Exception {
		if (user == null)
			throw new Exception();

		this.id = user.getId();
		this.email = user.getEmail();
		this.password=user.getPassword();		
		
		initVideos(user.getVideos());
	}

	private void initVideos(List<Video> videos) throws Exception {
		for(Video video : videos) {
			this.videos.add(new VideoDTO(video));
		}
	}
	
	
	public String getId() {
		return this.id;
	}

	public String getEmail() throws Exception {
		if(this.email==null || this.email.isBlank()) throw new Exception();
		return this.email;
	}

	public String getPassword() throws Exception {
		if(this.password==null || this.password.isBlank()) throw new Exception();
		return password;
	}
}
