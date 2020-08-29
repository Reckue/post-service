package com.reckue.post.services;

import com.reckue.post.models.Filters;
import com.reckue.post.utils.filters.FiltersUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface BaseService represents a common base service with CRUD operations for all services.
 *
 * @author Kamila Meshcheryakova
 */
public interface BaseService<T> {

    /**
     * This method is used to create an object of desired class.
     *
     * @param object of desired class
     * @return object of desired class
     */
    T create(T object);

    /**
     * This method is used to update data in an object of desired class.
     *
     * @param object of desired class
     * @return object of desired class
     */
    T update(T object);

    /**
     * This method is used to get all objects of desired class.
     *
     * @return list of objects of desired class
     */
    List<T> findAll();

    /**
     * This method is used to get all objects of desired class by parameters.
     *
     * @param filters compilation of limit, offset, sort and desc params
     * limit - quantity of objects
     * offset  - quantity to skip
     * sort - parameter for sorting
     * desc - sorting descending
     *
     * @return list of objects of desired class
     */
    default List<T> findAll(Filters filters) {
        final Filters validFilters = FiltersUtil.validateFilters(filters);
        return findAllByTypeAndDesc(validFilters.getSort(), validFilters.getDesc()).stream()
                .limit(validFilters.getLimit())
                .skip(validFilters.getOffset())
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Post sorted by the selected parameter for sorting
     * in descending order
     */
    List<T> findAllByTypeAndDesc(String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id of object
     * @return object of desired class
     */
    T findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
