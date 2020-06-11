package com.reckue.post.utils.converters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Check allows to check ConverterTest.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    private String id;
    private String name;
}
