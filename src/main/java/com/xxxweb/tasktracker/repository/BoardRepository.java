package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Board;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Board entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {}
