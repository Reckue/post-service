package com.reckue.post.services;

import com.reckue.post.models.nodes.PollNode;

import java.util.List;

/**
 * Interface PollNodeService represents service with CRUD operations.
 *
 * @author Grigoriev Viktor
 */
public interface PollNodeService {

        /**
         * This method is used to create an object of class PollNode.
         *
         * @param node object of class PollNode
         * @return node object of class PollNode
         */
        PollNode create(PollNode node);

        /**
         * This method is used to update data in an object of class PollNode.
         *
         * @param node object of class PollNode
         * @return node object of class PollNode
         */
        PollNode update(PollNode node);

        /**
         * This method is used to get all objects of class PollNode.
         *
         * @return list of objects of class PollNode
         */
        List<PollNode> findAll();

        /**
         * This method is used to get all objects of class PollNode by parameters.
         *
         * @param limit  quantity of objects
         * @param offset quantity to skip
         * @param sort   parameter for sorting
         * @param desc   sorting descending
         * @return list of objects of class PollNode
         */
        List<PollNode> findAll(Integer limit, Integer offset, String sort, Boolean desc);


        /**
         * This method is used to get an object by id.
         *
         * @param id object
         * @return post object of class PollNode
         */
        PollNode findById(String id);

        /**
         * This method is used to delete an object by id.
         *
         * @param id object
         */
        void deleteById(String id);
}
