package ru.practicum.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.StatsDto;
import ru.practicum.service.model.Stats;

import static ru.practicum.service.model.StatsConstant.DATE_TIME_PATTERN;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    static StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = DATE_TIME_PATTERN)
    Stats inputDTOToEntity(StatsDto inputDTO);
}
