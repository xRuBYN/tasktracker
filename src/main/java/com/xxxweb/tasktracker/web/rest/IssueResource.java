package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.repository.IssueRepository;
import com.xxxweb.tasktracker.service.IssueService;
import com.xxxweb.tasktracker.service.dto.IssueDTO;
import com.xxxweb.tasktracker.service.dto.IssueRequestDto;
import com.xxxweb.tasktracker.service.dto.MoveIssueDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.xxxweb.tasktracker.domain.Issue}.
 */
@RestController
@RequestMapping("/api")
public class IssueResource {

    private final Logger log = LoggerFactory.getLogger(IssueResource.class);

    private static final String ENTITY_NAME = "issue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IssueService issueService;

    public IssueResource(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/issue/{columnId}")
    public ResponseEntity<IssueDTO> createIssue(@Valid @RequestBody IssueRequestDto issueDTO, @PathVariable UUID columnId)
        throws URISyntaxException {
        log.debug("REST request to save Issue : {}", issueDTO);
        IssueDTO result = issueService.save(issueDTO, columnId);
        return ResponseEntity
            .created(new URI("/api/issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/issues/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable UUID id) {
        log.debug("REST request to delete Issue : {}", id);
        issueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/issues/{columnId}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByColumn(@PathVariable UUID columnId) {
        return ResponseEntity.ok(issueService.getAllIssuesByColumnId(columnId));
    }

    @DeleteMapping("/issue/{id}")
    public ResponseEntity<IssueDTO> getIssue(@PathVariable UUID id) {
        log.debug("REST request to get Issue : {}", id);
        IssueDTO issueDTO = issueService.getIssue(id);
        return ResponseEntity.ok(issueDTO);
    }

    @PutMapping("/issue/move")
    public ResponseEntity<Void> moveIssue(@RequestBody MoveIssueDto moveIssueDto) {
        issueService.moveIssue(moveIssueDto);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }

    @PutMapping("/issue/assignme/{issueId}")
    public ResponseEntity<Void> assignMe(@PathVariable UUID issueId) {
        issueService.assignMe(issueId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }

    @PutMapping("/issue/assign/{issueId}/{userId}")
    public ResponseEntity<Void> assignUser(@PathVariable UUID issueId, @PathVariable Long userId) {
        issueService.assignUser(issueId, userId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }

    @PutMapping("/issue/assign/remove/{issueId}")
    public ResponseEntity<Void> removeAssigned(@PathVariable UUID issueId) {
        issueService.removeAssigned(issueId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }
}
