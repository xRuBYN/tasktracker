package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ColumnEntity} and its DTO {@link ColumnEntityDTO}.
 */
@Mapper(componentModel = "spring")
public interface ColumnEntityMapper extends EntityMapper<ColumnEntityDTO, ColumnEntity> {
    ColumnEntityDTO toDto(ColumnEntity s);
}
