package com.tumblbug.project.contents.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tumblbug.project.contents.dto.ProjectDto;
import com.tumblbug.project.contents.dto.QProjectDto;
import com.tumblbug.project.contents.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.tumblbug.project.common.enums.ErrorCode.NOT_EXISTS_PROJECT;
import static com.tumblbug.project.contents.model.QCreator.creator;
import static com.tumblbug.project.contents.model.QProject.project;

@Repository
public class ProjectRepositorySupport extends QuerydslRepositorySupport {

    public ProjectRepositorySupport() {
        super(Project.class);
    }

    /**
     * 프로젝트 리스트
     *
     * @param pageable
     * @return
     */
    public Page<ProjectDto> getProjectList(final Pageable pageable) {
        final JPQLQuery<ProjectDto> query = from(project)
                .where(project.enabled.isTrue()).select(new QProjectDto(project, creator));

        final List<ProjectDto> projectDtoList = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(projectDtoList, pageable, query.fetchCount());
    }

    /**
     * 프로젝트 정보
     *
     * @param uuid 프로젝트 UUID
     * @return
     */
    public ProjectDto getProjectDetail(final UUID uuid) {
        BooleanExpression filter = project.enabled.isTrue()
                .and(project.id.eq(uuid));
        Project projectEntity = new JPAQueryFactory(getEntityManager()).selectFrom(project).where(filter).fetchOne();
        if (projectEntity == null) {
            throw new NoSuchElementException(NOT_EXISTS_PROJECT.getMessage());
        }
        return new ProjectDto(projectEntity);
    }
}
