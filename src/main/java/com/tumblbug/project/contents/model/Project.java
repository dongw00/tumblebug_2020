package com.tumblbug.project.contents.model;

import com.tumblbug.project.common.converters.BooleanConverter;
import com.tumblbug.project.common.enums.Status;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.tumblbug.project.common.enums.Status.*;

@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "creator_id")
    private Creator creator;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "donation_target_amount")
    private int donationTargetAmount;

    @Formula("(select ifnull(sum(d.amount), 0) from Donation d where d.project_id = id)")
    private Integer donationAmount;

    @Formula("(select count(*) from Donation d where d.project_id = id)")
    private Integer donationCount;

    @Convert(converter = BooleanConverter.class)
    private boolean enabled;

    public Project() {
        this.enabled = true;
    }

    @Builder
    public Project(String title, String description, Creator creator, LocalDateTime startDate, LocalDateTime endDate, int donationTargetAmount) {
        this();
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.startDate = startDate;
        this.endDate = endDate;
        this.donationTargetAmount = donationTargetAmount;
        this.donationAmount = donationAmount == null ? 0 : donationAmount;
        this.donationCount = donationCount == null ? 0 : donationCount;

        changeStatus(startDate, endDate, donationAmount, donationTargetAmount);
    }

    public void changeStatus(LocalDateTime startDate, LocalDateTime endDate, int donationAmount, int donationTargetAmount) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(endDate)) {
            this.status = READY;
        } else if ((startDate.equals(now) || startDate.isBefore(now)) && now.isBefore(endDate)) {
            this.status = PENDING;
        } else if ((endDate.equals(now) || endDate.isBefore(now)) && (donationAmount >= donationTargetAmount)) {
            this.status = SUCCESS;
        } else {
            this.status = FAIL;
        }
    }
}
