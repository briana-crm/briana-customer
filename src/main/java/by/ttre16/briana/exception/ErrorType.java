package by.ttre16.briana.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    APP_ERROR("APP_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND("DATA_NOT_FOUND", HttpStatus.UNPROCESSABLE_ENTITY),
    VALIDATION_ERROR("VALIDATION_ERROR", HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST("WRONG_REQUEST", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final HttpStatus status;
}
