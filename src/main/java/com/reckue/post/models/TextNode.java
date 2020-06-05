package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class TextNode represents model of text fields.
 *
 * @author Iveri Narozashvili
 */
@Data
@Document
public class TextNode {
	
	@Id
	private String id;
	
	private String content;
}
