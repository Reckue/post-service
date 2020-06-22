package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import java.util.List;

/**
 * Class PollNodeResponse represents au outgoing DTO for receiving type of node such as poll.
 *
 * @author Viktor Grigoriev
 */
@Data
public class PollNodeResponse implements NodeContent {

    private String id;
    private List<String> items;
}
