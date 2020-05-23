package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class TextNode {
	
	@Id
	private String id;
	
	private String content;
}
