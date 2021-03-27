package by.ttre16.briana.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorInfo {
    private final String url;
    private final ErrorType errorType;
}
