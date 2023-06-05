package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.service.ProjectSecurityProxy;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import com.xxxweb.tasktracker.service.dto.ProjectRequestDto;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link com.xxxweb.tasktracker.domain.Project}.
 */
@RestController
@RequestMapping("/api/project")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectSecurityProxy projectService;

    public ProjectResource(ProjectSecurityProxy projectSecurityProxy) {
        this.projectService = projectSecurityProxy;
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        log.debug("REST request to delete Project : {}", id);
        projectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequestDto projectDTO) {
        ProjectDTO project = projectService.createProject(projectDTO);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/edit/{projectId}")
    public ResponseEntity<Project> editProject(@RequestBody ProjectRequestDto projectDTO, @PathVariable UUID projectId) {
        Project project = projectService.editProjectName(projectDTO, projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/add/{projectId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable UUID projectId, @RequestBody List<Long> userIds) {
        projectService.addUserToProject(projectId, userIds);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove/{projectId}/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable UUID projectId, @PathVariable Long userId) {
        projectService.deleteUserFromProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/projects")
    public ResponseEntity<List<ProjectDTO>> getUserProjects() {
        return ResponseEntity.ok(projectService.getAllAssignedProject());
    }

    @GetMapping("/user/projects/me")
    public ResponseEntity<List<ProjectDTO>> getOwnProjects() {
        return ResponseEntity.ok(projectService.getOwnProject());
    }
}
