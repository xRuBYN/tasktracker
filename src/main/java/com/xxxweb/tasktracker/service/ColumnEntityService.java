package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.repository.ColumnEntityRepository;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.service.mapper.ColumnEntityMapper;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ColumnEntity}.
 */
@Service
@Transactional
public class ColumnEntityService {

    private final Logger log = LoggerFactory.getLogger(ColumnEntityService.class);

    private final ColumnEntityRepository columnEntityRepository;

    private final ColumnEntityMapper columnEntityMapper;

    public ColumnEntityService(ColumnEntityRepository columnEntityRepository, ColumnEntityMapper columnEntityMapper) {
        this.columnEntityRepository = columnEntityRepository;
        this.columnEntityMapper = columnEntityMapper;
    }

    /**
     * Save a columnEntity.
     *
     * @param columnEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public ColumnEntityDTO save(ColumnEntityDTO columnEntityDTO) {
        log.debug("Request to save ColumnEntity : {}", columnEntityDTO);
        ColumnEntity columnEntity = columnEntityMapper.toEntity(columnEntityDTO);
        columnEntity = columnEntityRepository.save(columnEntity);
        return columnEntityMapper.toDto(columnEntity);
    }

    /**
     * Update a columnEntity.
     *
     * @param columnEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public ColumnEntityDTO update(ColumnEntityDTO columnEntityDTO) {
        log.debug("Request to update ColumnEntity : {}", columnEntityDTO);
        ColumnEntity columnEntity = columnEntityMapper.toEntity(columnEntityDTO);
        columnEntity = columnEntityRepository.save(columnEntity);
        return columnEntityMapper.toDto(columnEntity);
    }

    /**
     * Partially update a columnEntity.
     *
     * @param columnEntityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ColumnEntityDTO> partialUpdate(ColumnEntityDTO columnEntityDTO) {
        log.debug("Request to partially update ColumnEntity : {}", columnEntityDTO);

        return columnEntityRepository
            .findById(columnEntityDTO.getId())
            .map(existingColumnEntity -> {
                columnEntityMapper.partialUpdate(existingColumnEntity, columnEntityDTO);

                return existingColumnEntity;
            })
            .map(columnEntityRepository::save)
            .map(columnEntityMapper::toDto);
    }

    /**
     * Get all the columnEntities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ColumnEntityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ColumnEntities");
        return columnEntityRepository.findAll(pageable).map(columnEntityMapper::toDto);
    }

    /**
     * Get one columnEntity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ColumnEntityDTO> findOne(UUID id) {
        log.debug("Request to get ColumnEntity : {}", id);
        return columnEntityRepository.findById(id).map(columnEntityMapper::toDto);
    }

    /**
     * Delete the columnEntity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete ColumnEntity : {}", id);
        columnEntityRepository.deleteById(id);
    }
}
