package ru.practicum.service.service;

import ru.practicum.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepositoryService {

    void create(StatsDto inputDTO);

    List<StatsDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
