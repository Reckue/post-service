package com.reckue.post.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CheckResponse allows to check ConverterTest.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckResponse {

    private String id;
    private String name;
}
