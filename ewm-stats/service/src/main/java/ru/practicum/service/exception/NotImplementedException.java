package ru.practicum.service.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotImplementedException extends RuntimeException {

    private final String message;
}