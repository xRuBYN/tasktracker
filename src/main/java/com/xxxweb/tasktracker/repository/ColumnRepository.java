package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Long> {}
