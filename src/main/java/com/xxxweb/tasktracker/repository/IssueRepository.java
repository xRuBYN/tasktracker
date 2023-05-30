package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Column;
import com.xxxweb.tasktracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {}
