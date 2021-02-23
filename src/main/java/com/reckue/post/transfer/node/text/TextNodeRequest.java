package com.reckue.post.transfer.node.text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextNodeRequest implements Serializable {

    private String value;

}
