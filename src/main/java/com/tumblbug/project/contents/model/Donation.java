package com.tumblbug.project.contents.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Min(value = 0, message = "후원금액이 0보다 작을 수 없습니다.")
    @Max(value = 100000000, message = "후원금액이 100,000,000을 초과할 수 없습니다.")
    @Column(name = "amount", nullable = false)
    private int amount;

    @Builder
    public Donation(Project project, int amount) {
        this.project = project;
        this.amount = amount;
    }
}
