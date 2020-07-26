package com.tumblbug.project.contents.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDonation {

    @ApiModelProperty(value = "프로젝트 UUID")
    private UUID projectId;

    @NotNull(message = "후원금액을 입력해주세요.")
    @Min(value = 0, message = "후원금액이 0보다 작을 수 없습니다.")
    @Max(value = 100000000, message = "후원금액이 100,000,000을 초과할 수 없습니다.")
    @ApiModelProperty(value = "후원금액", example = "100000")
    private int amount;
}
