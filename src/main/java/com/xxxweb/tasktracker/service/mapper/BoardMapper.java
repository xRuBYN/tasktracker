package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.Board;
import com.xxxweb.tasktracker.service.dto.BoardDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Board} and its DTO {@link BoardDTO}.
 */
@Mapper(componentModel = "spring")
public interface BoardMapper extends EntityMapper<BoardDTO, Board> {}
