package com.tumblbug.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS(OK, "Success"), ERROR(INTERNAL_SERVER_ERROR, "Internal Server Error"),

    NOT_EXISTS_PROJECT(BAD_REQUEST, "존재하지 않는 프로젝트입니다."), UNVALID_UUID(BAD_REQUEST, "유효하지 않는 UUID 형식입니다.");

    private final HttpStatus status;
    private final String message;
}
