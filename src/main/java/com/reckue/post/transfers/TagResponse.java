package com.reckue.post.transfers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagResponse {

    private String id;
    private String name;
}
