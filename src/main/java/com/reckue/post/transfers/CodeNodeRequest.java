package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CodeNodeRequest implements NodeContent {

    @NotNull
    private LangType language;

    @NotNull
    private String content;
}
