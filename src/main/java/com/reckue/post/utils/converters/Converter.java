package com.reckue.post.utils.converters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

/**
 * Method converts one object to another.
 */
@NoArgsConstructor
@Data
public class Converter {

    private static ModelMapper mapper;

    /**
     * This method convert
     *
     * @param src  object to be converted
     * @param dist class type of destination object
     * @param <T>  convertible type
     * @return converted object
     */
    public static <T> T convert(Object src, Class<T> dist) {
        return mapper.map(src, dist);
    }
}
