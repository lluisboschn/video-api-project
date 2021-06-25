package com.tcm.video.domain;

import java.util.UUID;

import com.tcm.video.application.dto.VideoDTO;

public class Video {

	private String id;
	private String name;
	private String url;

	public Video(String id, String name, String url) throws Exception {
		if (url == null || url.isBlank())
			throw new Exception();

		if (name == null || name.equals(""))
			throw new Exception();

		this.url = url;
		this.name = name;
		this.id = id;
	}

	public Video(VideoDTO video) throws Exception {
		if (video == null)
			throw new Exception();
		this.url = video.getUrl();
		this.name = video.getName();
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return this.name;
	}

	public void updateVideo(VideoDTO videoDTO) throws Exception {
		if (videoDTO == null)
			throw new Exception();

		this.name = videoDTO.getName();
		this.url = videoDTO.getUrl();
	}

}
