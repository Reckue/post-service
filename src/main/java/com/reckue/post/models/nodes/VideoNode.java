package com.reckue.post.models.nodes;

import com.reckue.post.utils.NodeWrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class VideoNode is responsible for displaying video.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class VideoNode {

    private String videoUrl;
}
