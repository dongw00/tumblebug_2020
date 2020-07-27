package com.tumblbug.project.contents.dto;

import com.tumblbug.project.common.enums.ProjectSort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static com.tumblbug.project.common.enums.ProjectSort.START_DATE;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomRequest {

    private int page;
    private int size;

    private Sort.Direction direction;
    private ProjectSort sort = START_DATE;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction != null ? direction : Sort.Direction.ASC;
    }

    public void setSort(String field) {
        this.sort = ProjectSort.find(field);
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size, direction, sort.getField());
    }
}
