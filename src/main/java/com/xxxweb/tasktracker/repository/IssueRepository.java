package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Issue;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Issue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
    @Query("select issue from Issue issue where issue.assigned.login = ?#{principal.username}")
    List<Issue> findByAssignedIsCurrentUser();

    List<Issue> findAllByColumnId(UUID id);
}
