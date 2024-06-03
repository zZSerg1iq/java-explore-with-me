package ru.practicum.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.StatsDto;
import ru.practicum.service.repository.StatsRepository;
import ru.practicum.service.exception.ValidException;
import ru.practicum.service.mapper.StatsMapper;
import ru.practicum.service.model.ResultEntity;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsRepositoryServiceImpl implements StatsRepositoryService {

    private final StatsRepository statsRepository;

    @Override
    public void create(StatsDto inputDTO) {
        statsRepository.save(StatsMapper.INSTANCE.inputDTOToEntity(inputDTO));
    }

    @Override
    public List<StatsDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {

        if (start.isAfter(end)) {
            throw ValidException.builder().message("Start date cannot be after end date").build();
        }

        // уникальные ли айпи
        if (unique) {
            // указаны адреса?
            if (uris == null) {
                System.out.println("1");
                return toStatsOutputDTOs(statsRepository.findAllByDate_UniqueIp(start, end));
            } else {
                System.out.println("2");
                return toStatsOutputDTOs(statsRepository.findAllByUriAndDate_UniqueIp(start, end, uris));
            }
        } else {
            if (uris == null) {
                System.out.println("3");
                return toStatsOutputDTOs(statsRepository.findAllByDate(start, end));
            } else {
                System.out.println("4");
                System.out.println(start + " " + end);
                return toStatsOutputDTOs(statsRepository.findAllByDateAndUri(start, end, uris));
            }
        }
    }

    private List<StatsDto> toStatsOutputDTOs(List<ResultEntity> stats) {
        Map<String, StatsDto> statsDTOMap = new HashMap<>();

        System.out.println("size: "+stats.size());
        for (ResultEntity stat : stats) {
            System.out.println(stat.getIp());
            String key = stat.getApp() + "_" + stat.getUri();
            if (!statsDTOMap.containsKey(key)) {
                statsDTOMap.put(key, StatsDto.builder()
                        .app(stat.getApp())
                        .uri(stat.getUri())
                        .hits(1L)
                        .build());
            } else {
                StatsDto dto = statsDTOMap.get(key);
                dto.setHits(dto.getHits() + 1);
            }
        }

        return statsDTOMap.values().stream()
                .sorted(Comparator.comparingLong(StatsDto::getHits).reversed())
                .collect(Collectors.toList());
    }
}
