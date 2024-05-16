package enoca.challenge.shopping.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionHandler(GlobalException globalException) {
        var status = globalException.getHttpStatus();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                globalException.getMessage(), Instant.now());

        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionHandler(ConstraintViolationException notValidException) {
        var status = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                notValidException
                        .getConstraintViolations()
                        .stream()
                        .findFirst()
                        .orElseThrow()
                        .getMessage(),
                Instant.now());

        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionHandler(TransactionSystemException notValidException) {
        var status = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse("Please check your updated data.",
                Instant.now());

        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception exception) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(), Instant.now());

        return new ResponseEntity<>(exceptionResponse, status);
    }
}
