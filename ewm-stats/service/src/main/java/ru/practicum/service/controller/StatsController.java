package ru.practicum.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatsDto;
import ru.practicum.service.service.StatsRepositoryService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.service.model.StatsConstant.DATE_TIME_PATTERN;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsRepositoryService statsRepositoryService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    void createStats(@Valid @RequestBody final StatsDto inputDTO) {

        log.info("START endpoint `method:POST /hit` (create stats), for uri: {}.", inputDTO.getUri());

        statsRepositoryService.create(inputDTO);
    }

    @GetMapping("/stats")
    List<StatsDto> getStats(@RequestParam() @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime start,
                            @RequestParam() @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime end,
                            @RequestParam(required = false) List<String> uris,
                            @RequestParam(defaultValue = "false") boolean unique) {

        log.info("START endpoint `method:GET /stats` (get all stats).");

        return statsRepositoryService.get(start, end, uris, unique);
    }
}
