package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TagResponse {

    @ApiModelProperty(notes = "Database generated tag ID")
    private String id;

    @ApiModelProperty(notes = "Tag name")
    private String name;
}
