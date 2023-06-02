package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.domain.Issue;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.service.dto.IssueDTO;
import com.xxxweb.tasktracker.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Issue} and its DTO {@link IssueDTO}.
 */
@Mapper(componentModel = "spring")
public interface IssueMapper extends EntityMapper<IssueDTO, Issue> {
    @Mapping(target = "assigned", source = "assigned", qualifiedByName = "userId")
    IssueDTO toDto(Issue s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
