package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class ListNode is responsible for displaying list of content.
 *
 * @author Iveri Narozashvili
 */
@Data
@Document
public class ListNode implements NodeContent {

	private List<String> content;
}