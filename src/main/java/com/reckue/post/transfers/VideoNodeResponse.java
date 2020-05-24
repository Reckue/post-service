package com.reckue.post.transfers;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class VideoNodeResponse {

    @Id
    private String id;
    private String videoUrl;
}
