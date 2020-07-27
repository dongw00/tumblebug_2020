package com.tumblbug.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ProjectSort {

    START_DATE("startDate", "시작일 순"), END_DATE("endDate", "마감일 순"),
    TARGET_AMOUNT("donationTargetAmount", "목표액 순"), DONATION_AMOUNT("donationAmount", "후원액 순");

    private static final Map<String, ProjectSort> map = Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(ProjectSort::getField, Function.identity())));
    private final String field;
    private final String description;

    public static ProjectSort find(String field) {
        return Optional.ofNullable(map.get(field)).orElse(START_DATE);
    }
}
