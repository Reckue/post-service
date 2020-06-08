package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.PostResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Interface NodeApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/nodes"})
public interface NodeApi {

    @ApiOperation(value = "Add a node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully added"),
            @ApiResponse(code = 409, message = "The node you want to add already exists"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    NodeResponse create(NodeRequest nodeRequest);

    @ApiOperation(value = "Update a node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    NodeResponse update(String id, NodeRequest nodeRequest);

    @ApiOperation(value = "View a list of available nodes", response = NodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of nodes successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    List<NodeResponse> findAll(int limit, int offset, String sort, boolean desc);

    @ApiOperation(value = "Search a node with an ID", response = NodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    NodeResponse findById(String id);

    @ApiOperation(value = "Delete a node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    void deleteById(String id);
}
