package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ColumnEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColumnEntityRepository extends JpaRepository<ColumnEntity, UUID> {
    List<ColumnEntity> findColumnEntitiesByProjectId(UUID id);
}
