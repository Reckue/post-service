package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class PollNode is responsible for the voting system.
 *
 * @author Viktor Grigorirev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PollNode implements NodeContent {

    @Id
    private String id;

    private List<String> items;
}
