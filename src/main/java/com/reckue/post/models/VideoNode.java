package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class VideoNode is responsible for displaying video.
 *
 * @author Iveri Narozashvili
 */
@Data
@Document
public class VideoNode {

	@Id
	private String id;
	
	private String videoUrl;
}
