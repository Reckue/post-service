package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class AudioNode is responsible for playing audio.
 *
 * @author Daria Smirnova
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AudioNode implements NodeContent {

    private String audioUrl;
}
