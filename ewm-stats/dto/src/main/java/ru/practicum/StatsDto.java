package ru.practicum;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
public class StatsDto {

    @NotBlank(message = "The app field cannot be empty.")
    private String app;

    @NotBlank(message = "The uri field cannot be empty.")
    private String uri;

    @NotBlank(message = "The ip field cannot be empty.")
    @JsonIgnore
    private String ip;

    @NotNull(message = "The timestamp field cannot be null.")
    @JsonIgnore
    private String timestamp;

    private long hits;
}
