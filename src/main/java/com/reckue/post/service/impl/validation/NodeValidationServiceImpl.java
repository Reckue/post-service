package com.reckue.post.service.impl.validation;

import com.reckue.post.model.Node;
import com.reckue.post.model.type.StatusType;
import com.reckue.post.service.validation.NodeValidationService;
import com.reckue.post.util.security.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.reckue.post.model.Role.ADMIN;
import static com.reckue.post.model.Role.MODERATOR;
import static com.reckue.post.model.type.StatusType.*;

@Service
public class NodeValidationServiceImpl implements NodeValidationService {

    @Override
    public void validateNodeStatusOnUpdate(Node node, StatusType nextNodeStatus) {
        if (node.getStatus() == null) {
            throw new RuntimeException("Parameter node.status can't be null");
        }

        Optional.of(node).ifPresent(n -> {
            /*
             If current status is active and the next status is deleted,
             wherein the current user isn't author of this node
             then disable to delete (change status to deleted) this node.
            */
            if (ACTIVE == n.getStatus() && nextNodeStatus == DELETED
                    && !CurrentUser.getId().equals(n.getUserId())) {
                throw new RuntimeException("Current user hasn't permission to delete this n");
            }

            /*
             If current status of node is deleted or banned
             and current user is going to change the status to active,
             wherein current user hasn't administrator's or moderator's permissions (roles)
             then disable to activate (change status to active) this n.
            */
            if ((n.getStatus() == DELETED || n.getStatus() == BANNED || n.getStatus() == MODERATED)
                    && (nextNodeStatus == ACTIVE && !CurrentUser.getRoles().containsAll(List.of(ADMIN, MODERATOR)))) {
                throw new RuntimeException("Current user hasn't permission to delete this n");
            }

            /*
             If next status of node is banned or moderated,
             wherein the current user hasn't administrator's or moderator's permissions (roles)
             then disable to ban or moderate (change status to banned or moderated) this node.
            */
            if ((nextNodeStatus == BANNED || nextNodeStatus == MODERATED)
                    && !CurrentUser.getRoles().containsAll(List.of(ADMIN, MODERATOR))) {
                throw new RuntimeException("Current user hasn't permission to ban or moderate this node");
            }
        });
    }
}
