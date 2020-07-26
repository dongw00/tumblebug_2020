package com.tumblbug.project.contents.service;

import com.tumblbug.project.contents.dto.CreateDonation;
import com.tumblbug.project.contents.model.Donation;
import com.tumblbug.project.contents.model.Project;
import com.tumblbug.project.contents.repository.DonationRepository;
import com.tumblbug.project.contents.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final ProjectRepository projectRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createDonation(final CreateDonation req) {
        Project project = projectRepository.findById(req.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않은 프로젝트입니다."));
        donationRepository.save(Donation.builder().project(project).amount(req.getAmount()).build());
    }
}
