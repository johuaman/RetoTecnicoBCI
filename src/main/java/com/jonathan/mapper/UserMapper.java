package com.jonathan.mapper;

import com.jonathan.dto.request.UserRequestDTO;
import com.jonathan.dto.response.UserResponseDTO;
import com.jonathan.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "lastLogin", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isActive", constant = "true")
    UserEntity toEntity(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(UserEntity entity);
}