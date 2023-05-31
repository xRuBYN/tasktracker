package com.xxxweb.tasktracker.service.mapper;

import com.xxxweb.tasktracker.domain.Board;
import com.xxxweb.tasktracker.domain.Project;
import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.service.dto.BoardDTO;
import com.xxxweb.tasktracker.service.dto.ProjectDTO;
import com.xxxweb.tasktracker.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    ProjectDTO toDto(Project s);

    Project toEntity(ProjectDTO projectDTO);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("userIdSet")
    default Set<UserDTO> toDtoUserIdSet(Set<User> user) {
        return user.stream().map(this::toDtoUserId).collect(Collectors.toSet());
    }

    @Named("boardId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BoardDTO toDtoBoardId(Board board);
}
