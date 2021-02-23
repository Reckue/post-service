//package com.reckue.post.service.impl;
//
//
//import com.reckue.post.exception.ReckueAccessDeniedException;
//import com.reckue.post.exception.ReckueIllegalArgumentException;
//import com.reckue.post.exception.model.comment.CommentNotFoundException;
//import com.reckue.post.exception.model.post.PostNotFoundException;
//import com.reckue.post.model.Comment;
//import com.reckue.post.model.Node;
//import com.reckue.post.model.Role;
//import com.reckue.post.model.type.ParentType;
//import com.reckue.post.processor.notnull.NotNullArgs;
//import com.reckue.post.repository.CommentRepository;
//import com.reckue.post.repository.NodeRepository;
//import com.reckue.post.repository.PostRepository;
//import com.reckue.post.service.CommentService;
//import com.reckue.post.service.NodeService;
//import com.reckue.post.util.security.CurrentUser;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang.SerializationUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * Class CommentServiceImpl represents realization of CommentService.
// *
// * @author Artur Magomedov
// */
//@Service
//@RequiredArgsConstructor
//public class CommentServiceImpl implements CommentService {
//
//    private final CommentRepository commentRepository;
//    private final NodeRepository nodeRepository;
//    private final PostRepository postRepository;
//    private final NodeService nodeService;
//
//    @Override
//    @Transactional
//    @NotNullArgs
//    public Comment create(Comment comment) {
//        comment.setUserId(CurrentUser.getId());
//        // to set default value as null
//        if (comment.getCommentId().length() < 7) {
//            comment.setCommentId(null);
//        }
//        validateCreatingComment(comment);
//
//        Comment storedComment = (Comment) SerializationUtils.clone(comment);
//        List<Node> nodeList = null;
//
//        if (comment.getNodes() != null) {
//            nodeList = comment.getNodes();
//            comment.setNodes(null);
//            storedComment = commentRepository.save(comment);
//            final String commentId = storedComment.getId();
//
//            nodeList.forEach(node -> {
//                node.setParentId(commentId);
//                node.setParentType(ParentType.COMMENT);
//                nodeService.create(node);
//            });
//        }
//        storedComment.setNodes(nodeList);
//        return storedComment;
//    }
//
//    public void validateCreatingComment(Comment comment) {
//        if (!postRepository.existsById(comment.getPostId())) {
//            throw new PostNotFoundException(comment.getPostId());
//        }
//        if (comment.getCommentId() != null && !commentRepository.existsById(comment.getCommentId())) {
//            throw new CommentNotFoundException(comment.getCommentId());
//        }
//    }
//
//    @Override
//    public Comment update(Comment comment) {
//        if (comment.getId() == null) {
//            throw new ReckueIllegalArgumentException("The parameter is null");
//        }
//        if (!CurrentUser.getId().equals(comment.getUserId()) && !CurrentUser.getRoles().contains(Role.ADMIN)) {
//            throw new ReckueAccessDeniedException("The operation is forbidden");
//        }
//
//        if (!comment.getNodes().isEmpty()) {
//            comment.getNodes().forEach(node -> {
//                node.setParentId(comment.getId());
//                nodeService.create(node);
//            });
//        }
//
//        Comment savedComment = commentRepository
//                .findById(comment.getId())
//                .orElseThrow(() -> new CommentNotFoundException(comment.getId()));
//        savedComment.setCommentId(comment.getCommentId().length() > 7 ? comment.getCommentId() : null);
//        savedComment.setNodes(comment.getNodes());
//
//        return commentRepository.save(savedComment);
//    }
//
//    @Override
//    public List<Comment> findAll() {
//        List<Comment> comments = commentRepository.findAll();
//
//        for (Comment comment : comments) {
//            List<Node> nodes = nodeRepository.findAllByParentId(comment.getId());
//            comment.setNodes(nodes);
//        }
//        return comments;
//    }
//
//    @Override
//    public List<Comment> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
//        if (limit == null) limit = 10;
//        if (offset == null) offset = 0;
//        if (StringUtils.isEmpty(sort)) sort = "id";
//        if (desc == null) desc = false;
//
//        if (limit < 0 || offset < 0) {
//            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
//        }
//        return findAllByTypeAndDesc(sort, desc).stream()
//                .limit(limit)
//                .skip(offset)
//                .collect(Collectors.toList());
//    }
//
//    public List<Comment> findAllByTypeAndDesc(String sort, boolean desc) {
//        if (desc) {
//            List<Comment> comments = findAllBySortType(sort);
//            Collections.reverse(comments);
//            return comments;
//        }
//        return findAllBySortType(sort);
//    }
//
//    public List<Comment> findAllBySortType(String sort) {
//        switch (sort) {
//            case "id":
//                return findAllAndSortById();
//            case "userId":
//                return findAllAndSortByUserId();
//            case "postId":
//                return findAllAndSortByPostId();
//            case "createdDate":
//                return findAllAndSortByCreatedDate();
//            case "modificationDate":
//                return findAllAndSortByModificationDate();
//        }
//        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
//    }
//
//    private List<Comment> findAllAndSortByModificationDate() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(Comment::getModificationDate))
//                .collect(Collectors.toList());
//    }
//
//    public List<Comment> findAllAndSortById() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(Comment::getId))
//                .collect(Collectors.toList());
//    }
//
//    public List<Comment> findAllAndSortByUserId() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(Comment::getUserId))
//                .collect(Collectors.toList());
//    }
//
//    public List<Comment> findAllAndSortByPostId() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(Comment::getPostId))
//                .collect(Collectors.toList());
//    }
//
//    public List<Comment> findAllAndSortByCreatedDate() {
//        return findAll().stream()
//                .sorted(Comparator.comparing(Comment::getCreatedDate))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Comment findById(String id) {
//        Optional<Comment> comment = commentRepository.findById(id);
//        List<Node> nodes = nodeRepository.findAllByParentId(id);
//        if (comment.isEmpty())
//            throw new CommentNotFoundException(id);
//
//        comment.ifPresent(p -> p.setNodes(nodes));
//        return comment.get();
//    }
//
//    @Override
//    // FIXME: correct the realization of this method
//    public List<Comment> findAllByUserId(String userId, Integer limit, Integer offset) {
//        if (limit == null) limit = 10;
//        if (offset == null) offset = 0;
//        if (limit < 0 || offset < 0) {
//            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
//        }
//        return commentRepository.findAllByUserId(userId)
//                .stream()
//                .limit(limit)
//                .skip(offset)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteById(String id) {
//        if (!commentRepository.existsById(id)) {
//            throw new CommentNotFoundException(id);
//        }
//        Optional<Comment> comment = commentRepository.findById(id);
//        if (comment.isPresent()) {
//            if (CurrentUser.getId().equals(comment.get().getUserId()) || CurrentUser.getRoles().contains(Role.ADMIN)){
//                commentRepository.deleteById(id);
//            } else {
//                throw new ReckueAccessDeniedException("The operation is forbidden");
//            }
//        }
//    }
//
//}
