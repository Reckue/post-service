package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class ImageNodeRequest represents an incoming DTO for adding type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ImageNodeRequest implements NodeContent {

    @NotNull
    private String imageUrl;
}
