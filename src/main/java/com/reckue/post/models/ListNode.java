package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class ListNode {

	@Id
	private String id;
	
	private List<String> content;
}