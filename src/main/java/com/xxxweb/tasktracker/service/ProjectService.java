package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.Board;
import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.repository.ProjectRepository;
import com.xxxweb.tasktracker.service.dto.BoardDTO;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import com.xxxweb.tasktracker.service.mapper.ProjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for managing {@link Project}.
 */
@Service
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final UserService userService;

    private final BoardService boardService;

    public ProjectService(
        ProjectRepository projectRepository,
        ProjectMapper projectMapper,
        UserService userService,
        BoardService boardService
    ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.boardService = boardService;
    }

    /**
     * Save a project.
     *
     * @param projectDTO the entity to save.
     * @return the persisted entity.
     */
    public ProjectDTO save(ProjectDTO projectDTO) {
        log.debug("Request to save Project : {}", projectDTO);
        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    /**
     * Update a project.
     *
     * @param projectDTO the entity to save.
     * @return the persisted entity.
     */
    public ProjectDTO update(ProjectDTO projectDTO) {
        log.debug("Request to update Project : {}", projectDTO);
        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    /**
     * Partially update a project.
     *
     * @param projectDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectDTO> partialUpdate(ProjectDTO projectDTO) {
        log.debug("Request to partially update Project : {}", projectDTO);

        return projectRepository
            .findById(projectDTO.getId())
            .map(existingProject -> {
                projectMapper.partialUpdate(existingProject, projectDTO);

                return existingProject;
            })
            .map(projectRepository::save)
            .map(projectMapper::toDto);
    }

    /**
     * Get all the projects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Projects");
        return projectRepository.findAll(pageable).map(projectMapper::toDto);
    }

    /**
     * Get all the projects with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ProjectDTO> findAllWithEagerRelationships(Pageable pageable) {
        return projectRepository.findAllWithEagerRelationships(pageable).map(projectMapper::toDto);
    }

    /**
     * Get one project by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectDTO> findOne(UUID id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findOneWithEagerRelationships(id).map(projectMapper::toDto);
    }

    /**
     * Delete the project by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        User user = userService.getCurrentUserByLogin();
        BoardDTO board = new BoardDTO();
        board.setName(projectDTO.getName());
        Board savedBoard = boardService.save(board);
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setUser(user);
        project.setBoard(savedBoard);
        Project saved = projectRepository.save(project);
        return projectMapper.toDto(saved);
    }

    public Project getProjectById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Project editProjectName(ProjectDTO projectDTO, UUID id) {
        Project project = getProjectById(id);
        project.setName(projectDTO.getName());
        return projectRepository.save(project);
    }

    public void addUserToProject(UUID projectId, List<Long> userIds) {
        Project project = getProjectById(projectId);
        userIds.forEach(id -> {
            User user = userService.getUserById(id);
            project.getUsers().add(user);
        });
        projectRepository.save(project);
    }

    public void deleteUserFromProject(UUID projectId, Long userId) {
        Project project = getProjectById(projectId);
        User user = userService.getUserById(userId);
        project.getUsers().remove(user);
        projectRepository.save(project);
    }
}
