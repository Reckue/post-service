package com.reckue.post.transfers;

import lombok.Data;

import java.util.List;

@Data
public class ListNodeResponse {

    private String id;
    private List<String> content;
}
