package com.tumblbug.project.contents.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomRequest {

    private static final int SIZE = 10;

    private int page = 1;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, SIZE, Sort.Direction.DESC, "startDate");
    }
}
