package com.reckue.post.utils.converters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Converter {

    private static ModelMapper mapper;

    public static <T> T convert(Object src, Class<T> dist) {
        return mapper.map(src, dist);
    }
}