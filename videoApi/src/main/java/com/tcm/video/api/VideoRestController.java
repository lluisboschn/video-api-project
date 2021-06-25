package com.tcm.video.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcm.video.application.VideoController;
import com.tcm.video.application.dto.VideoDTO;

@RestController
public class VideoRestController {

	@PostMapping("/users/{userId}/videos")
	public VideoDTO createVideo(@PathVariable String userId, @RequestBody VideoDTO videoDTO) throws Exception {
		return new VideoController().createVideo(userId, videoDTO);
	}

	@GetMapping("/users/{userId}/videos/{videoId}")
	public VideoDTO getVideo(@PathVariable String userId, @PathVariable String videoId) throws Exception {
		return new VideoController().getVideoByUser(userId, videoId);
	}

	@PutMapping("/users/{userId}/videos/{videoId}")
	public VideoDTO updateVideo(@PathVariable String userId, @PathVariable String videoId, @RequestBody VideoDTO video)
			throws Exception {
		return new VideoController().updateVideo(userId, videoId, video);
	}

}
