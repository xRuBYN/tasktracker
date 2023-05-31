package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    @Override
    ProjectDTO toDto(Project entity);
}
