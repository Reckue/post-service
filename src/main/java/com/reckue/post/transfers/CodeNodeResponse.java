package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import com.reckue.post.utils.NodeContent;
import lombok.Data;

@Data
public class CodeNodeResponse implements NodeContent {

    private LangType language;
    private String content;
}
