package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

@Data
public class ImageNodeResponse implements NodeContent {

    private String id;
    private String imageUrl;
}
