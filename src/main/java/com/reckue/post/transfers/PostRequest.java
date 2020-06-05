package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.StatusType;
import com.reckue.post.models.Tag;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class PostRequest {

    @NotNull
    private String title;

    private List<Node> nodes;

    private String username;

    @Size(max=128)
    private String source;

    private List<Tag> tags;

    @NotNull
    private long published;

    @NotNull
    private long changed;

    @NotNull
    private StatusType status;
}
