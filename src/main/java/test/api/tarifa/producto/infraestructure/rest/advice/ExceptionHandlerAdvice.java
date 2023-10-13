package test.api.tarifa.producto.infraestructure.rest.advice;

import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.FORMAT_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.api.tarifa.producto.domain.model.dto.ErrorResponseDTO;
import test.api.tarifa.producto.domain.model.exception.NotResultException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotResultException.class})
  public ResponseEntity<Object> handleThorwError(NotResultException ex, WebRequest request) {
    log.error("Error for reason: ", ex);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
    return new ResponseEntity<>(ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now().format(formatter))
        .path(((ServletWebRequest) request).getRequest().getRequestURI())
        .status(ex.getError().getValue())
        .error(ex.getError().getMessage())
        .build(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
