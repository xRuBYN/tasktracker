package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.service.ProjectService;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectResource {

    public final ProjectService projectService;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = projectService.createProject(projectDTO);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/edit/{projectId}")
    public ResponseEntity<Project> editProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long projectId) {
        Project project = projectService.editProjectName(projectDTO, projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/add/{projectId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long projectId, @RequestBody List<Long> userIds) {
        projectService.addUserToProject(projectId, userIds);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove/{projectId}/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.deleteUserFromProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
