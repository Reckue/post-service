package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import lombok.Data;

@Data
public class CodeNodeRequest {

    private LangType language;
    private String content;
}
