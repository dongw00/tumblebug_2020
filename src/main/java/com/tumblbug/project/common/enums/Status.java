package com.tumblbug.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    READY("준비중"), PENDING("진행중"), SUCCESS("성공"), FAIL("실패");

    private final String description;
}
