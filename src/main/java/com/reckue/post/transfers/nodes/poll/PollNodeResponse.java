package com.reckue.post.transfers.nodes.poll;

import com.reckue.post.transfers.nodes.NodeParentResponse;
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
@EqualsAndHashCode(callSuper = false)
@Builder
public class PollNodeResponse implements NodeParentResponse {

    private String title;
    private List<String> items;
}
