package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ErrorDto;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidDataException.class })
    public ResponseEntity<?> handleInvalidDataException(InvalidDataException ex) {
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).error(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<?> handlenotfoundException(NotFoundException ex) {
        ErrorDto error = ErrorDto.builder().code(HttpStatus.NOT_FOUND.value()).error(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
