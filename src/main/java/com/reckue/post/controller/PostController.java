package com.reckue.post.controller;

import com.reckue.post.controller.api.PostApi;
import com.reckue.post.exception.ReckueUnauthorizedException;
import com.reckue.post.model.Post;
import com.reckue.post.service.SecurityService;
import com.reckue.post.service.impl.PostServiceImpl;
import com.reckue.post.transfer.dto.post.PostFilterRequest;
import com.reckue.post.transfer.dto.post.PostRequest;
import com.reckue.post.transfer.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static com.reckue.post.util.converter.PostConverter.convert;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController
		implements PostApi
{

    private final PostServiceImpl postService;
    private final SecurityService securityService;

    /**
     * This type of request allows to create, process it using the converter and save.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param postRequest the object of class PostRequest
     * @param request     information for HTTP servlets
     * @return the object of class PostResponse
     */
    @PostMapping
    public PostResponse create(@RequestBody @Valid PostRequest postRequest, HttpServletRequest request) {
        return convert(postService.create(postRequest, securityService.checkAndGetInfo(request)));
    }

    @PostMapping("/publish/{id}")
    public void publishPost(@PathVariable String postId, HttpServletRequest request) {
		postService.publish(postId, securityService.checkAndGetInfo(request));
	}

    /**
     * This type of request allows to update by id the object, process it using the converter and save.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id          the object identifier
     * @param postRequest the object of class PostRequest
     * @param request     information for HTTP servlets
     * @return the object of class PostResponse
     */
    @PutMapping("/{id}")
    public PostResponse update(@PathVariable String id,
                               @RequestBody @Valid PostRequest postRequest,
                               HttpServletRequest request) {
        return convert(postService.update(id, postRequest, securityService.checkAndGetInfo(request)));
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class PostResponse
     */
    @GetMapping("/{id}")
    public PostResponse findById(@PathVariable String id) {
        return convert(postService.findById(id));
    }

	@Override
	public List<PostResponse> findByTitle(String title) {
		return null;
	}

	@Override
	public List<PostResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
		return null;
	}

	@Override
	public List<PostResponse> findAllByUserId(String userId, Integer limit, Integer offset) {
		return null;
	}

	/**
     * This type of request allows to get all the objects that meet the requirements, process it using the converter.
     *
     * @return list of given quantity of objects of class PostResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @PostMapping("/filter")
    public Page<Post> findAllByFilter(@RequestBody PostFilterRequest filter, Pageable pageable) {
        return postService.findAll(filter, pageable);
    }

    /**
     * This type of request allows to delete the object by id.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id      the object identifier
     * @param request information for HTTP servlets
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id, HttpServletRequest request) {
        postService.deleteById(id, securityService.checkAndGetInfo(request));
    }
}
