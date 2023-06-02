package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.repository.ColumnEntityRepository;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.service.dto.ColumnRequestDto;
import com.xxxweb.tasktracker.service.mapper.ColumnEntityMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for managing {@link ColumnEntity}.
 */
@Service
@Transactional
public class ColumnEntityService {

    private final Logger log = LoggerFactory.getLogger(ColumnEntityService.class);

    private final ColumnEntityRepository columnEntityRepository;

    private final ColumnEntityMapper columnEntityMapper;

    private final ProjectService projectService;

    public ColumnEntityService(
        ColumnEntityRepository columnEntityRepository,
        ColumnEntityMapper columnEntityMapper,
        ProjectService projectService
    ) {
        this.columnEntityRepository = columnEntityRepository;
        this.columnEntityMapper = columnEntityMapper;
        this.projectService = projectService;
    }

    /**
     * Save a columnEntity.
     *
     * @param columnEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public ColumnEntityDTO save(ColumnRequestDto columnEntityDTO, UUID projectId) {
        log.debug("Request to save ColumnEntity : {}", columnEntityDTO);
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.setName(columnEntityDTO.getName());
        Project project = projectService.getProjectById(projectId);
        columnEntity.setProject(project);
        columnEntity = columnEntityRepository.save(columnEntity);
        return columnEntityMapper.toDto(columnEntity);
    }

    /**
     * Delete the columnEntity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete ColumnEntity : {}", id);
        columnEntityRepository.deleteById(id);
    }

    public ColumnEntity getColumnEntityById(UUID id) {
        Optional<ColumnEntity> column = columnEntityRepository.findById(id);
        if (column.isPresent()) {
            return column.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ColumnEntityDTO getColumnEntity(UUID id) {
        log.debug("Request to get ColumnEntity : {}", id);
        ColumnEntity column = getColumnEntityById(id);
        ColumnEntityDTO columnDTO = columnEntityMapper.toDto(column);
        return columnDTO;
    }

    public List<ColumnEntityDTO> getColumnEntities(UUID projectId) {
        List<ColumnEntity> columns = columnEntityRepository.findColumnEntitiesByProjectId(projectId);
        return columns.stream().map(columnEntityMapper::toDto).collect(Collectors.toList());
    }
}
