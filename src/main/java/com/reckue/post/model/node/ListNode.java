package com.reckue.post.model.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class ListNode is responsible for displaying list of content.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ListNode implements Parent {

    private List<String> content;
}
