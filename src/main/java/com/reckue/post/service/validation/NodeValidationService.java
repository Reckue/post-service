package com.reckue.post.service.validation;

import com.reckue.post.model.Node;
import com.reckue.post.model.type.StatusType;

public interface NodeValidationService {

    void validateNodeStatusOnCreate(Node node);

    void validateNodeStatusOnUpdate(Node node, StatusType nextNodeStatus);

}
