package by.ttre16.briana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionInfoHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> dataIntegrityViolation(HttpServletRequest req) {
        return sendError(req, ErrorType.VALIDATION_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalRequestDataException.class,
            ServerWebInputException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<?> illegalRequestData(HttpServletRequest request) {
        return sendError(request, ErrorType.WRONG_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> dataNotFound(HttpServletRequest request) {
        return sendError(request, ErrorType.DATA_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, Error.class})
    public ResponseEntity<?> internalServerError(HttpServletRequest request) {
        return sendError(request, ErrorType.APP_ERROR);
    }

    private ResponseEntity<?> sendError(HttpServletRequest req, ErrorType type) {
        return ResponseEntity
                .status(type.getStatus())
                .body(new ErrorInfo(req.getRequestURL().toString(), type));
    }
}
