package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.repository.ColumnEntityRepository;
import com.xxxweb.tasktracker.service.ColumnEntityService;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.service.dto.ColumnRequestDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link com.xxxweb.tasktracker.domain.ColumnEntity}.
 */
@RestController
@RequestMapping("/api")
public class ColumnEntityResource {

    private final Logger log = LoggerFactory.getLogger(ColumnEntityResource.class);

    private static final String ENTITY_NAME = "columnEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ColumnEntityService columnEntityService;

    private final ColumnEntityRepository columnEntityRepository;

    public ColumnEntityResource(ColumnEntityService columnEntityService, ColumnEntityRepository columnEntityRepository) {
        this.columnEntityService = columnEntityService;
        this.columnEntityRepository = columnEntityRepository;
    }

    /**
     * {@code POST  /column-entities} : Create a new columnEntity.
     *
     * @param columnEntityDTO the columnEntityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new columnEntityDTO, or with status {@code 400 (Bad Request)} if the columnEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/column/{projectId}")
    public ResponseEntity<ColumnEntityDTO> createColumnEntity(
        @Valid @RequestBody ColumnRequestDto columnEntityDTO,
        @PathVariable UUID projectId
    ) throws URISyntaxException {
        log.debug("REST request to save ColumnEntity : {}", columnEntityDTO);
        ColumnEntityDTO result = columnEntityService.save(columnEntityDTO, projectId);
        return ResponseEntity
            .created(new URI("/api/column/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/column/{id}")
    public ResponseEntity<Void> deleteColumnEntity(@PathVariable UUID id) {
        log.debug("REST request to delete ColumnEntity : {}", id);
        columnEntityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/column/{id}")
    public ResponseEntity<ColumnEntityDTO> getColumnEntity(@PathVariable UUID id) {
        log.debug("REST request to get ColumnEntity : {}", id);
        return ResponseEntity.ok(columnEntityService.getColumnEntity(id));
    }

    @GetMapping("/columns/{projectId}")
    public ResponseEntity<List<ColumnEntityDTO>> getColumnEntities(@PathVariable UUID projectId) {
        log.debug("REST request to get ColumnEntities by Project Id : {}", projectId);
        return ResponseEntity.ok(columnEntityService.getColumnEntities(projectId));
    }
}
