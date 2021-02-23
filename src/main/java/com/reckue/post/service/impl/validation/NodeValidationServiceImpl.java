package com.reckue.post.service.impl.validation;

import com.reckue.post.model.Node;
import com.reckue.post.model.type.StatusType;
import com.reckue.post.service.validation.NodeValidationService;
import com.reckue.post.util.security.CurrentUser;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.reckue.post.model.Role.ADMIN;
import static com.reckue.post.model.Role.MODERATOR;
import static com.reckue.post.model.type.StatusType.ACTIVE;

@Service
public class NodeValidationServiceImpl implements NodeValidationService {

    @Override
    public void validateNodeStatusOnCreate(Node node) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        StatusType currentStatus = node.getStatus();

        if (currentStatus != ACTIVE && currentStatus != null) {
            throw new RuntimeException("Status of node must be 'ACTIVE' on create");
        }
    }

    @Override
    public void validateNodeStatusOnUpdate(Node node, StatusType nextStatus) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        if (node.getStatus() == null) {
            throw new RuntimeException("The 'status' parameter can't be null");
        }

        if (node.getStatus() != ACTIVE) {
            if (!CurrentUser.getRoles().contains(ADMIN) && !CurrentUser.getRoles().contains(MODERATOR)) {
                throw new PermissionDeniedDataAccessException("Current user hasn't permission to change status",
                        new RuntimeException());
            }
        }
    }

}
