package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.domain.Issue;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.repository.IssueRepository;
import com.xxxweb.tasktracker.service.dto.IssueDTO;
import com.xxxweb.tasktracker.service.dto.IssueRequestDto;
import com.xxxweb.tasktracker.service.dto.MoveIssueDto;
import com.xxxweb.tasktracker.service.mapper.IssueMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for managing {@link Issue}.
 */
@Service
@Transactional
public class IssueService {

    private final Logger log = LoggerFactory.getLogger(IssueService.class);

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final ColumnEntityService columnEntityService;

    private final UserService userService;

    public IssueService(
        IssueRepository issueRepository,
        IssueMapper issueMapper,
        ColumnEntityService columnEntityService,
        UserService userService
    ) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.columnEntityService = columnEntityService;
        this.userService = userService;
    }

    public IssueDTO save(IssueRequestDto issueDTO, UUID columnId) {
        ColumnEntity column = columnEntityService.getColumnEntityById(columnId);
        log.debug("Request to save Issue : {}", issueDTO);
        Issue issue = new Issue();
        issue.setColumn(column);
        issue.setName(issueDTO.getName());
        issue.setPriority(issueDTO.getPriority());
        Issue savedIssue = issueRepository.save(issue);
        return issueMapper.toDto(savedIssue);
    }

    public void delete(UUID id) {
        log.debug("Request to delete Issue : {}", id);
        issueRepository.deleteById(id);
    }

    public Issue getIssueById(UUID id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return issue.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public IssueDTO getIssue(UUID id) {
        Issue issue = getIssueById(id);
        return issueMapper.toDto(issue);
    }

    public List<IssueDTO> getAllIssuesByColumnId(UUID columnId) {
        log.debug("Request to get All Issue by Column.");
        List<Issue> issues = issueRepository.findAllByColumnId(columnId);
        return issues.stream().map(issueMapper::toDto).collect(Collectors.toList());
    }

    public void moveIssue(MoveIssueDto moveIssueDto) {
        log.debug("Request to move Issue.");
        ColumnEntity newColumn = columnEntityService.getColumnEntityById(moveIssueDto.getNewColumnId());
        Issue issue = getIssueById(moveIssueDto.getIssueId());
        issue.setColumn(newColumn);
        issueRepository.save(issue);
    }

    public void assignMe(UUID issueId) {
        log.debug("Request to assign me to Issue.");
        Issue issue = getIssueById(issueId);
        User user = userService.getCurrentUserByLogin();
        issue.setAssigned(user);
        issueRepository.save(issue);
    }

    public void assignUser(UUID issueId, Long userId) {
        log.debug("Request to assign me to Issue.");
        Issue issue = getIssueById(issueId);
        User user = userService.getUserById(userId);
        issue.setAssigned(user);
        issueRepository.save(issue);
    }

    public void removeAssigned(UUID issueId) {
        log.debug("Request to assign me to Issue.");
        Issue issue = getIssueById(issueId);
        issue.setAssigned(null);
        issueRepository.save(issue);
    }
}
