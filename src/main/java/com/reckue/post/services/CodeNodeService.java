package com.reckue.post.services;


import com.reckue.post.models.CodeNode;

import java.util.List;

/**
 * Interface CodeNodeService represents service with CRUD operations.
 *
 * @author Kamila Meshcheryakova
 */
public interface CodeNodeService {

    /**
     * This method is used to create an object of class CodeNode.
     *
     * @param codeNode object of class CodeNode
     * @return codeNode object of class CodeNode
     */
    CodeNode create(CodeNode codeNode);

    /**
     * This method is used to update data in an object of class CodeNode.
     *
     * @param codeNode object of class CodeNode
     * @return codeNode object of class CodeNode
     */
    CodeNode update(CodeNode codeNode);

    /**
     * This method is used to get all objects of class CodeNode.
     *
     * @return list of objects of class CodeNode
     */
    List<CodeNode> findAll();

    /**
     * This method is used to get all objects of class CodeNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class CodeNode
     */
    List<CodeNode> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return object of class CodeNode
     */
    CodeNode findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
