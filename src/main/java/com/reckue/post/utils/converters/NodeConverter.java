package com.reckue.post.utils.converters;

import com.reckue.post.models.Node;
import com.reckue.post.models.nodes.*;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import com.reckue.post.transfers.nodes.audio.AudioNodeRequest;
import com.reckue.post.transfers.nodes.audio.AudioNodeResponse;
import com.reckue.post.transfers.nodes.code.CodeNodeRequest;
import com.reckue.post.transfers.nodes.code.CodeNodeResponse;
import com.reckue.post.transfers.nodes.image.ImageNodeRequest;
import com.reckue.post.transfers.nodes.image.ImageNodeResponse;
import com.reckue.post.transfers.nodes.list.ListNodeRequest;
import com.reckue.post.transfers.nodes.list.ListNodeResponse;
import com.reckue.post.transfers.nodes.poll.PollNodeRequest;
import com.reckue.post.transfers.nodes.poll.PollNodeResponse;
import com.reckue.post.transfers.nodes.text.TextNodeRequest;
import com.reckue.post.transfers.nodes.text.TextNodeResponse;
import com.reckue.post.transfers.nodes.video.VideoNodeRequest;
import com.reckue.post.transfers.nodes.video.VideoNodeResponse;
import org.w3c.dom.Text;

/**
 * Class for converting NodeRequest object to Node and Node object to NodeResponse.
 *
 * @author Viktor Grigoriev
 */
public class NodeConverter {

    /**
     * Converts from NodeRequest to Node.
     *
     * @param nodeRequest the object of class NodeRequest
     * @return the object of class Node
     */
    public static Node convert(NodeRequest nodeRequest) {
        if (nodeRequest == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        Parent node = null;
        switch (nodeRequest.getType()) {
            case TEXT:
                node = TextNode.builder()
                        .content(((TextNodeRequest) nodeRequest.getNode()).getContent())
                        .build();
                break;
            case IMAGE:
                node = ImageNode.builder()
                        .imageUrl(((ImageNodeRequest) nodeRequest.getNode()).getImageUrl())
                        .build();
                break;
            case VIDEO:
                node = VideoNode.builder()
                        .videoUrl(((VideoNodeRequest) nodeRequest.getNode()).getVideoUrl())
                        .build();
                break;
            case CODE:
                node = CodeNode.builder()
                        .content(((CodeNodeRequest) nodeRequest.getNode()).getContent())
                        .language(((CodeNodeRequest) nodeRequest.getNode()).getLanguage())
                        .build();
                break;
            case LIST:
                node = ListNode.builder()
                        .content(((ListNodeRequest) nodeRequest.getNode()).getContent())
                        .build();
                break;
            case AUDIO:
                node = AudioNode.builder()
                        .audioUrl(((AudioNodeRequest) nodeRequest.getNode()).getAudioUrl())
                        .build();
                break;
            case POLL:
                node = PollNode.builder()
                        .title(((PollNodeRequest) nodeRequest.getNode()).getTitle())
                        .items(((PollNodeRequest) nodeRequest.getNode()).getItems())
                        .build();
                break;

        }
        return Node.builder()
                .userId(nodeRequest.getUserId())
                .source(nodeRequest.getSource())
                .type(nodeRequest.getType())
                .node(node)
                .published(nodeRequest.getPublished())
                .build();
    }

    /**
     * Converts from Node to NodeResponse.
     *
     * @param node the object of class Node
     * @return the object of class NodeResponse
     */
    public static NodeResponse convert(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        NodeParentResponse nodeResponse = null;
        switch (node.getType()) {
            case TEXT:
                nodeResponse = TextNodeResponse.builder()
                        .content(((TextNode) node.getNode()).getContent())
                        .build();
                break;
            case IMAGE:
                nodeResponse = ImageNodeResponse.builder()
                        .imageUrl(((ImageNode) node.getNode()).getImageUrl())
                        .build();
                break;
            case VIDEO:
                nodeResponse = VideoNodeResponse.builder()
                        .videoUrl(((VideoNode) node.getNode()).getVideoUrl())
                        .build();
                break;
            case CODE:
                nodeResponse = CodeNodeResponse.builder()
                        .content(((CodeNode) node.getNode()).getContent())
                        .language(((CodeNode) node.getNode()).getLanguage())
                        .build();
                break;
            case LIST:
                nodeResponse = ListNodeResponse.builder()
                        .content(((ListNode) node.getNode()).getContent())
                        .build();
                break;
            case AUDIO:
                nodeResponse = AudioNodeResponse.builder()
                        .audioUrl(((AudioNode) node.getNode()).getAudioUrl())
                        .build();
                break;
            case POLL:
                nodeResponse = PollNodeResponse.builder()
                        .title(((PollNode) node.getNode()).getTitle())
                        .items(((PollNode) node.getNode()).getItems())
                        .build();
                break;

        }

        return NodeResponse.builder()
                .id(node.getId())
                .userId(node.getUserId())
                .source(node.getSource())
                .type(node.getType())
                .node(nodeResponse)
                .status(node.getStatus())
                .published(node.getPublished())
                .build();
    }
}
