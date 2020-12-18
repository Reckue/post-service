package com.reckue.post.model.node;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

import java.io.Serializable;

/**
 * Interface Parent represents the common model for all kinds of nodes.
 *
 * @author Kamila Meshcheryakova
 */
@BsonDiscriminator(key = "_class")
public interface Parent extends Serializable {
}
