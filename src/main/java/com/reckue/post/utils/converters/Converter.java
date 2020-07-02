package com.reckue.post.utils.converters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Converter represents mapping of objects.
 *
 * @author Artur Magomedov
 */
@NoArgsConstructor
@Data
public class Converter {

    private static ModelMapper mapper = new ModelMapper();

    /**
     * This method converts one object to another.
     *
     * @param src  object to be converted
     * @param dest class type of destination object
     * @param <T>  convertible type
     * @return converted object
     */
    public static <T> T convert(Object src, Class<T> dest) {
        if (src == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return mapper.map(src, dest);
    }

    /**
     * This method converts one list to another.
     *
     * @param list list to be converted
     * @param dest class type of destination object
     * @param <T>  convertible type
     * @return converted list
     */
    public static <S, T> List<T> convert(List<S> list, Class<T> dest) {
        if (list == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return list
                .stream()
                .map(element -> mapper.map(element, dest))
                .collect(Collectors.toList());
    }
}
