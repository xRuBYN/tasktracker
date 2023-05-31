package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.Board;
import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.service.dto.BoardDTO;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ColumnEntity} and its DTO {@link ColumnEntityDTO}.
 */
@Mapper(componentModel = "spring")
public interface ColumnEntityMapper extends EntityMapper<ColumnEntityDTO, ColumnEntity> {
    @Mapping(target = "board", source = "board", qualifiedByName = "boardId")
    ColumnEntityDTO toDto(ColumnEntity s);

    @Named("boardId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BoardDTO toDtoBoardId(Board board);
}
