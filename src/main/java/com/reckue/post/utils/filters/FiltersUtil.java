package com.reckue.post.utils.filters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Filters;
import org.springframework.util.StringUtils;

public class FiltersUtil {

    public static Filters validateFilters(Filters filters) {
        return validateScope(Filters.builder()
                .limit(filters.getLimit() == null ? 10 : filters.getLimit())
                .offset(filters.getOffset() == null ? 0 : filters.getOffset())
                .sort(StringUtils.isEmpty(filters.getSort()) ? "id" : filters.getSort())
                .desc(filters.getDesc() == null ? false : filters.getDesc())
                .build());
    }

    public static Filters buildFiltersObject(Integer limit, Integer offset, String sort, Boolean desc) {
        return Filters.builder().limit(limit).offset(offset).sort(sort).desc(desc).build();
    }

    private static Filters validateScope(Filters filters) {
        if (filters.getLimit() < 0 || filters.getOffset() < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return filters;
    }
}
