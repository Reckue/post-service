package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CodeNodeRequest {

    @NotNull
    private LangType language;

    @NotNull
    private String content;
}
