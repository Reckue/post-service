package com.reckue.post.transfer.node.text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextNodeResponse implements Serializable {

    private String value;

}
