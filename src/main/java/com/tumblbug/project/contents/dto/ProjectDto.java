package com.tumblbug.project.contents.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.tumblbug.project.contents.model.Creator;
import com.tumblbug.project.contents.model.Project;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectDto {

    @ApiModelProperty(value = "프로젝트 제목", example = "맥북프로 2018 15인치")
    private String title;

    @ApiModelProperty(value = "창작자 이름", example = "홍길동")
    private String creatorName;

    @ApiModelProperty(value = "목표액", example = "500000")
    private int donationTarget;

    @ApiModelProperty(value = "후원수", example = "10")
    private int donationCount;

    @ApiModelProperty(value = "후원액", example = "100000")
    private int donationAmount;

    @ApiModelProperty(value = "프로젝트 상태", example = "진행중")
    private String status;

    @ApiModelProperty(value = "프로젝트 시작일", example = "2020-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "프로젝트 마감일", example = "2020-03-01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @QueryProjection
    public ProjectDto(Project project, Creator creator) {
        this.title = project.getTitle();
        this.creatorName = creator.getName();
        this.donationTarget = project.getDonationTargetAmount();
        this.donationCount = project.getDonationCount();
        this.donationAmount = project.getDonationAmount();
        this.status = project.getStatus().getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
    }

    public ProjectDto(Project project) {
        this.title = project.getTitle();
        this.creatorName = project.getCreator().getName();
        this.donationTarget = project.getDonationTargetAmount();
        this.donationCount = project.getDonationCount();
        this.donationAmount = project.getDonationAmount();
        this.status = project.getStatus().getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
    }
}
