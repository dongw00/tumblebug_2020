package com.tumblbug.project.contents.service;

import com.tumblbug.project.contents.dto.CreateProject;
import com.tumblbug.project.contents.dto.ProjectDto;
import com.tumblbug.project.contents.model.Creator;
import com.tumblbug.project.contents.model.Project;
import com.tumblbug.project.contents.repository.CreatorRepository;
import com.tumblbug.project.contents.repository.ProjectRepository;
import com.tumblbug.project.contents.repository.ProjectRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.tumblbug.project.common.enums.ErrorCode.NOT_EXISTS_PROJECT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final CreatorRepository creatorRepository;
    private final ProjectRepositorySupport projectRepositorySupport;
    private final ProjectRepository projectRepository;

    /**
     * 프로젝트 리스트
     *
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<ProjectDto> getProjectList(final Pageable pageable) {
        return projectRepositorySupport.getProjectList(pageable);
    }

    /**
     * 프로젝트 상세정보
     *
     * @param uuid 프로젝트 id
     * @return
     */
    @Transactional(readOnly = true)
    public ProjectDto getProjectDetail(final UUID uuid) {
        return projectRepositorySupport.getProjectDetail(uuid);
    }

    /**
     * 프로젝트 생성
     *
     * @param req
     */
    @Transactional(rollbackFor = Exception.class)
    public void createProject(final CreateProject req) {
        Creator creator = creatorRepository.findByEmail(req.getCreatorEmail())
                .orElseGet(() -> Creator.builder().name(req.getCreatorName())
                        .email(req.getCreatorEmail()).phone(req.getCreatorPhone()).build());

        projectRepository.save(Project.builder().title(req.getTitle()).description(req.getDescription())
                .creator(creator).startDate(req.getStartDate()).endDate(req.getEndDate())
                .donationTargetAmount(req.getDonationTargetAmount()).build());
    }

    /**
     * 프로젝트 삭제
     *
     * @param uuid 프로젝트 id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteProject(final UUID uuid) {
        Project project = projectRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException(NOT_EXISTS_PROJECT.getMessage()));

        projectRepository.delete(project);
    }
}
