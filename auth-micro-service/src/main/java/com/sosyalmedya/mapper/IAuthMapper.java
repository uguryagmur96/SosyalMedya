package com.sosyalmedya.mapper;

import com.sosyalmedya.dto.request.doRegisterRequestDto;
import com.sosyalmedya.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth  authFromDto(final doRegisterRequestDto dto);
}
