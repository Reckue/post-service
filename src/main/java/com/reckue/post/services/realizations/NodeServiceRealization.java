package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ReckueAccessDeniedException;
import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.exceptions.models.comment.CommentNotFoundException;
import com.reckue.post.exceptions.models.nodes.NodeNotFoundException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.repositories.CommentRepository;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class NodeServiceRealization represents realization of NodeService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class NodeServiceRealization implements NodeService {

    private final NodeRepository nodeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TokenStore tokenStore;

    /**
     * This method is used to create an object of class Node.
     *
     * @param node  object of class Node
     * @param token user token
     * @return node object of class Node
     */
    @Override
    public Node create(Node node, String token) {
        if (node == null) {
            throw new RuntimeException("Node is null");
        }
        String userId = (String) tokenStore.readAccessToken(token)
                .getAdditionalInformation().get("userId");
        node.setUserId(userId);

        validateCreatingNode(node);
        // todo: add status validation
        return nodeRepository.save(node);
    }

    /**
     * This method is used to check node validation.
     * Throws {@link PostNotFoundException} in case if such post isn't contained in database.
     * Throws {@link CommentNotFoundException} in case if such comment isn't contained in database.
     *
     * @param node object of class Node
     */
    public void validateCreatingNode(Node node) {
        ParentType parentType = node.getParentType();
        switch (parentType) {
            case POST:
                if (!postRepository.existsById(node.getParentId())) {
                    throw new PostNotFoundException(node.getParentId());
                }
                break;
            case COMMENT:
                if (!commentRepository.existsById(node.getParentId())) {
                    throw new CommentNotFoundException(node.getParentId());
                }
                break;
        }
    }

    /**
     * This method is used to update data in an object of class Node.
     * Throws {@link NodeNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link ReckueIllegalArgumentException} in case
     * if parameter equals null.
     * Throws {@link ReckueAccessDeniedException} in case if the user isn't an node owner or
     * hasn't admin authorities.
     *
     * @param node  object of class Node
     * @param token user token
     * @return node object of class Node
     */
    @Override
    public Node update(Node node, String token) {
        if (node.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }

        Node savedNode = nodeRepository
                .findById(node.getId())
                .orElseThrow(() -> new NodeNotFoundException(node.getId()));
        savedNode.setType(node.getType());
        //FIXME we don't change content of concrete node - for TEXT node it's "content" - must be correct!
        // now content of savedNode is the same how it was, it can't save the content of node
        savedNode.setParentType(node.getParentType());
        savedNode.setSource(node.getSource());
        savedNode.setStatus(node.getStatus());

        Map<String, Object> tokenInfo = tokenStore.readAccessToken(token).getAdditionalInformation();
        if (!tokenInfo.get("userId").equals(savedNode.getUserId())
                && !tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
            throw new ReckueAccessDeniedException("The operation is forbidden");
        }

        return nodeRepository.save(savedNode);
    }

    /**
     * This method is used to get all objects of class Node.
     *
     * @return list of objects of class Node
     */
    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class Node by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class Node with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @Override
    public List<Node> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (StringUtils.isEmpty(sort)) sort = "id";
        if (desc == null) desc = false;

        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return findAllByTypeAndDesc(sort, desc).stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Node sorted by the selected parameter for sorting
     * in descending order
     */
    public List<Node> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Node> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
            return nodes;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: type, status, source, createdDate, modificationDate, userId default - id
     * @return list of objects of class Node sorted by the selected parameter for sorting
     */
    public List<Node> findAllBySortType(String sort) {
        switch (sort) {
            case "type":
                return findAllAndSortByType();
            case "status":
                return findAllAndSortByStatus();
            case "source":
                return findAllAndSortBySource();
            case "createdDate":
                return findAllAndSortByCreatedDate();
            case "modificationDate":
                return findAllAndSortByModificationDate();
            case "userId":
                return findAllAndSortByUserId();
            case "id":
                return findAllAndSortById();
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    /**
     * This method is used to sort objects by modificationDate.
     *
     * @return list of objects of class Node sorted by modificationDate
     */
    private List<Node> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getModificationDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class Node sorted by id
     */
    public List<Node> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by status.
     *
     * @return list of objects of class Node sorted by status
     */
    public List<Node> findAllAndSortByStatus() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getStatus))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by type.
     *
     * @return list of objects of class Node sorted by type
     */
    public List<Node> findAllAndSortByType() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getType))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by userId.
     *
     * @return list of objects of class Node sorted by userId
     */
    public List<Node> findAllAndSortByUserId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getUserId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by source.
     *
     * @return list of objects of class Node sorted by source
     */
    public List<Node> findAllAndSortBySource() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getSource))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by createdDate.
     *
     * @return list of objects of class Node sorted by createdDate
     */
    public List<Node> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getCreatedDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link NodeNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return post object of class Node
     */
    @Override
    public Node findById(String id) {
        return nodeRepository.findById(id).orElseThrow(
                () -> new NodeNotFoundException(id));
    }

    /**
     * This method is used to get a list of nodes by user id.
     *
     * @param userId user identificator
     * @return list of objects of class Node
     */
    @Override
    // FIXME: correct the realization of this method
    public List<Node> findAllByUserId(String userId) {
        return nodeRepository.findAllByUserId(userId)
                .stream()
                .limit(10)
                .skip(0)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link NodeNotFoundException} in case if such object isn't contained in database.
     * Throws {@link ReckueAccessDeniedException} in case if the user isn't an node owner or
     * hasn't admin authorities.
     *
     * @param id    object
     * @param token user token
     */
    @Override
    public void deleteById(String id, String token) {
        if (!nodeRepository.existsById(id)) {
            throw new NodeNotFoundException(id);
        }
        Map<String, Object> tokenInfo = tokenStore.readAccessToken(token).getAdditionalInformation();
        Optional<Node> node = nodeRepository.findById(id);
        if (node.isPresent()) {
            String nodeUser = node.get().getUserId();
            if (tokenInfo.get("userId").equals(nodeUser) || tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
                nodeRepository.deleteById(id);
            } else {
                throw new ReckueAccessDeniedException("The operation is forbidden");
            }
        }

    }

    /**
     * This method is used to delete all nodes.
     */
    @Deprecated
    @Override
    public void deleteAll() {
        nodeRepository.deleteAll();
    }
}
