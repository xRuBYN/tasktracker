package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.repository.ColumnEntityRepository;
import com.xxxweb.tasktracker.service.ColumnEntityService;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

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
    @PostMapping("/column-entities")
    public ResponseEntity<ColumnEntityDTO> createColumnEntity(@Valid @RequestBody ColumnEntityDTO columnEntityDTO)
        throws URISyntaxException {
        log.debug("REST request to save ColumnEntity : {}", columnEntityDTO);
        if (columnEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new columnEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ColumnEntityDTO result = columnEntityService.save(columnEntityDTO);
        return ResponseEntity
            .created(new URI("/api/column-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /column-entities/:id} : Updates an existing columnEntity.
     *
     * @param id the id of the columnEntityDTO to save.
     * @param columnEntityDTO the columnEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated columnEntityDTO,
     * or with status {@code 400 (Bad Request)} if the columnEntityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the columnEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/column-entities/{id}")
    public ResponseEntity<ColumnEntityDTO> updateColumnEntity(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody ColumnEntityDTO columnEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ColumnEntity : {}, {}", id, columnEntityDTO);
        if (columnEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, columnEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!columnEntityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ColumnEntityDTO result = columnEntityService.update(columnEntityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, columnEntityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /column-entities/:id} : Partial updates given fields of an existing columnEntity, field will ignore if it is null
     *
     * @param id the id of the columnEntityDTO to save.
     * @param columnEntityDTO the columnEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated columnEntityDTO,
     * or with status {@code 400 (Bad Request)} if the columnEntityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the columnEntityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the columnEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/column-entities/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ColumnEntityDTO> partialUpdateColumnEntity(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody ColumnEntityDTO columnEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ColumnEntity partially : {}, {}", id, columnEntityDTO);
        if (columnEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, columnEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!columnEntityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ColumnEntityDTO> result = columnEntityService.partialUpdate(columnEntityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, columnEntityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /column-entities} : get all the columnEntities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of columnEntities in body.
     */
    @GetMapping("/column-entities")
    public ResponseEntity<List<ColumnEntityDTO>> getAllColumnEntities(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ColumnEntities");
        Page<ColumnEntityDTO> page = columnEntityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /column-entities/:id} : get the "id" columnEntity.
     *
     * @param id the id of the columnEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the columnEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/column-entities/{id}")
    public ResponseEntity<ColumnEntityDTO> getColumnEntity(@PathVariable UUID id) {
        log.debug("REST request to get ColumnEntity : {}", id);
        Optional<ColumnEntityDTO> columnEntityDTO = columnEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(columnEntityDTO);
    }

    /**
     * {@code DELETE  /column-entities/:id} : delete the "id" columnEntity.
     *
     * @param id the id of the columnEntityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/column-entities/{id}")
    public ResponseEntity<Void> deleteColumnEntity(@PathVariable UUID id) {
        log.debug("REST request to delete ColumnEntity : {}", id);
        columnEntityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
