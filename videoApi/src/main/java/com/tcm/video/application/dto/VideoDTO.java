package com.tcm.video.application.dto;

import com.tcm.video.domain.Video;

public class VideoDTO {

	private String id;
	private String name;
	private String url;

	public VideoDTO() {

	}

	public VideoDTO(Video video) throws Exception {
		if (video == null)
			throw new Exception();
		this.id = video.getId();
		this.url = video.getUrl();
		this.name = video.getName();
	}

	public String getId() {
		return id;
	}

	public String getName() throws Exception {
		if (name == null || name.isBlank())
			throw new Exception();
		return name;
	}

	public String getUrl() throws Exception {
		if (url == null || url.isBlank())
			throw new Exception();
		return url;
	}

}
