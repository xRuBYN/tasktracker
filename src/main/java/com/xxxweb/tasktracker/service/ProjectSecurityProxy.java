package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.Authority;
import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.security.AuthoritiesConstants;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import com.xxxweb.tasktracker.service.dto.ProjectRequestDto;
import com.xxxweb.tasktracker.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProjectSecurityProxy implements ProjectManager {

    private final UserService userService;

    private final ProjectService projectService;

    public ProjectSecurityProxy(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public void deleteUserFromProject(UUID projectId, Long userId) {
        if (!hasRole()) {
            throw new BadRequestAlertException("", "", "");
        }
        projectService.deleteUserFromProject(projectId, userId);
    }

    @Override
    public void addUserToProject(UUID projectId, List<Long> userIds) {
        if (!hasRole()) {
            throw new NotAuthorityException();
        }
        projectService.addUserToProject(projectId, userIds);
    }

    @Override
    public Project editProjectName(ProjectRequestDto projectDTO, UUID id) {
        if (!hasRole()) {
            throw new NotAuthorityException();
        }
        return projectService.editProjectName(projectDTO, id);
    }

    @Override
    public ProjectDTO createProject(ProjectRequestDto projectDTO) {
        if (!hasRole()) {
            throw new NotAuthorityException();
        }
        return projectService.createProject(projectDTO);
    }

    @Override
    public void delete(UUID id) {
        if (!hasRole()) {
            throw new NotAuthorityException();
        }
        projectService.delete(id);
    }

    @Override
    public List<ProjectDTO> getAllAssignedProject() {
        return projectService.getAllAssignedProject();
    }

    @Override
    public List<ProjectDTO> getOwnProject() {
        return projectService.getOwnProject();
    }

    @Override
    public Project getProjectById(UUID id) {
        return projectService.getProjectById(id);
    }

    private boolean hasRole() {
        User user = userService.getCurrentUserByLogin();
        return user.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getName(), AuthoritiesConstants.MANAGER));
    }
}
