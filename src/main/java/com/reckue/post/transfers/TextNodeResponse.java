package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

@Data
public class TextNodeResponse implements NodeContent {

    private String content;
}
