package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.repository.ProjectRepository;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public Project createProject(ProjectDTO projectDTO) {
        User user = userService.getCurrentUserByLogin();
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setUser(user);
        projectRepository.save(project);
        return project;
    }

    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Project editProjectName(ProjectDTO projectDTO, Long id) {
        Project project = getProjectById(id);
        project.setName(projectDTO.getName());
        return projectRepository.save(project);
    }

    public void addUserToProject(Long projectId, List<Long> userIds) {
        Project project = getProjectById(projectId);
        userIds.forEach(id -> {
            User user = userService.getUserById(id);
            project.getUsers().add(user);
        });
        projectRepository.save(project);
    }

    public void deleteUserFromProject(Long projectId, Long userId) {
        Project project = getProjectById(projectId);
        User user = userService.getUserById(userId);
        project.getUsers().remove(user);
        projectRepository.save(project);
    }
}
