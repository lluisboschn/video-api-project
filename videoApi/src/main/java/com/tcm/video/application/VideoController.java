package com.tcm.video.application;

import com.tcm.video.application.dto.VideoDTO;
import com.tcm.video.domain.User;
import com.tcm.video.domain.Video;
import com.tcm.video.persistence.UserRepository;
import com.tcm.video.persistence.VideoRepository;

public class VideoController {

	public VideoDTO createVideo(String userId, VideoDTO videoDTO) throws Exception {

		User user = new UserRepository().getUser(userId);

		Video video = new Video(videoDTO);

		user.addVideo(video);

		return new VideoDTO(video);
	}

	public VideoDTO getVideoByUser(String userId, String videoId) throws Exception {
		User user = new UserRepository().getUser(userId);
		Video video = user.getVideoById(videoId);
		return new VideoDTO(video);
	}

	public VideoDTO updateVideo(String userId, String videoId, VideoDTO videoDTO) throws Exception {
		User user = new UserRepository().getUser(userId);
		Video video = user.getVideoById(videoId);
		video.updateVideo(videoDTO);

		new VideoRepository().updateVideo(video);
		return new VideoDTO(video);
	}
}
