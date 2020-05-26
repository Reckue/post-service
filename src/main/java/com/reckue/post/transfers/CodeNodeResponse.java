package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import lombok.Data;

@Data
public class CodeNodeResponse {

    private String id;
    private LangType language;
    private String content;
}
