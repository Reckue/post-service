package com.reckue.post.transfers.nodes.poll;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Class PollNodeResponse represents au outgoing DTO for receiving type of node such as poll.
 *
 * @author Viktor Grigoriev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PollNodeResponse extends NodeParentResponse {

    private String id;
    private String title;
    private List<String> items;
}
