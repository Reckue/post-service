package com.reckue.post.transfer.node.poll;

import com.reckue.post.transfer.node.NodeParentResponse;
import lombok.*;

import java.util.List;

/**
 * Class PollNodeResponse represents au outgoing DTO for receiving type of node such as poll.
 *
 * @author Viktor Grigoriev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollNodeResponse implements NodeParentResponse {

    private String title;
    private List<String> items;
}
