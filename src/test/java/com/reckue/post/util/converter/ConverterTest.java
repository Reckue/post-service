package com.reckue.post.util.converter;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.objects.Check;
import com.reckue.post.objects.CheckResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class ConverterTest allows to test all methods of class Converter.
 *
 * @author Kamila Meshcheryakova
 */
class ConverterTest extends PostServiceApplicationTests {

    @Test
    void convertCheckToCheckResponse() {
        Check check = Check.builder()
                .id("1")
                .name("egnaf")
                .build();

        CheckResponse expected = CheckResponse.builder()
                .id(check.getId())
                .name(check.getName())
                .build();

        CheckResponse actual = Converter.convert(check, CheckResponse.class);
        assertEquals(expected, actual);
    }

//    @Test
//    void checkCheckIsNull() {
//        Throwable exception = assertThrows(ReckueIllegalArgumentException.class,
//                () -> Converter.convert((Object) null, CheckResponse.class));
//        assertEquals("Null parameters are not allowed", exception.getMessage());
//    }

    @Test
    void convertListToAnotherList() {
        Check check1 = Check.builder()
                .id("1")
                .name("daria")
                .build();
        Check check2 = Check.builder()
                .id("2")
                .name("camelya")
                .build();
        List<Check> checks = Stream.of(check1, check2).collect(Collectors.toList());

        CheckResponse checkResponse1 = CheckResponse.builder()
                .id(check1.getId())
                .name(check1.getName())
                .build();
        CheckResponse checkResponse2 = CheckResponse.builder()
                .id(check2.getId())
                .name(check2.getName())
                .build();

        List<CheckResponse> expected = Stream.of(checkResponse1, checkResponse2).collect(Collectors.toList());

        List<CheckResponse> actual = Converter.convert(checks, CheckResponse.class);
        assertEquals(expected, actual);
    }

    @Test
    void checkListIsNull() {
        List<Check> checks = null;
        Throwable exception = assertThrows(ReckueIllegalArgumentException.class,
                () -> Converter.convert(checks, CheckResponse.class));
        assertEquals("Null parameters are not allowed", exception.getMessage());
    }
}
