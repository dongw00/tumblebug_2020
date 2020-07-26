package com.tumblbug.project.contents.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProject {

    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\s]+$", message = "프로젝트 제목은 한글, 숫자, 영문만 가능합니다.")
    @NotBlank(message = "프로젝트 제목은 필수로 입력 해야 합니다.")
    @Length(max = 50, message = "프로젝트 제목은 50자 이내 입니다.")
    @ApiModelProperty(value = "프로젝트 제목", example = "시각예술 작가로 부터 온 최소한의 작품")
    private String title;

    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z~!@#$%^&*()_+|<>?:{}.,\\s]+$", message = "프로젝트 설명은 한글, 숫자, 영문, 특수문자만 가능합니다.")
    @NotBlank(message = "프로젝트 설명은 필수로 입력 해야 합니다.")
    @Length(max = 255, message = "프로젝트 설명은 255자 이내 입니다.")
    @ApiModelProperty(value = "프로젝트 설명", example = "여백이나 그림들이 들어있는 하나의 통일된 형태의 낱장들을 모아서 한쪽을 묶어 놓은 것' 으로 정의되고 있습니다.")
    private String description;

    @Pattern(regexp = "^[가-힣a-zA-Z_]+$", message = "창작자 이름은 한글, 숫자, 영문, _만 가능합니다.")
    @NotBlank(message = "창작자 이름은 필수로 입력 해야 합니다.")
    @Length(max = 20, message = "창작자 이름은 20자 이내입니다.")
    @ApiModelProperty(value = "창작자 이름", example = "mwapress")
    private String creatorName;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @ApiModelProperty(value = "창작자 이메일", example = "hellomwa.kr@gmail.com")
    private String creatorEmail;

    @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$", message = "휴대폰 형식이 올바르지 않습니다.")
    @ApiModelProperty(value = "창작자 전화번호", example = "010-3849-3023")
    private String creatorPhone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "프로젝트 시작일", example = "2020-01-01 15:00:00")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "프로젝트 종료일", example = "2020-03-01 22:00:00")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "프로젝트 목표액", example = "1000000")
    @Min(value = 0, message = "목표액은 0보다 작을 수 없습니다.")
    @Max(value = 100000000, message = "목표액은 100,000,000을 초과할 수 없습니다.")
    private int donationTargetAmount;
}
