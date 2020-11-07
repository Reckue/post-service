package com.reckue.post.model.node;

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
public class AudioNode implements Parent {

    private String audioUrl;
}
