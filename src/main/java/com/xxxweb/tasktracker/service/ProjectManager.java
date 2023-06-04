package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import com.xxxweb.tasktracker.service.dto.ProjectRequestDto;
import java.util.List;
import java.util.UUID;

public interface ProjectManager {
    void deleteUserFromProject(UUID projectId, Long userId);

    void addUserToProject(UUID projectId, List<Long> userIds);

    Project editProjectName(ProjectRequestDto projectDTO, UUID id);

    ProjectDTO createProject(ProjectRequestDto projectDTO);

    void delete(UUID id);

    List<ProjectDTO> getAllAssignedProject();

    List<ProjectDTO> getOwnProject();

    Project getProjectById(UUID id);
}
