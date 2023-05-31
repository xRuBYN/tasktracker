package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {}
