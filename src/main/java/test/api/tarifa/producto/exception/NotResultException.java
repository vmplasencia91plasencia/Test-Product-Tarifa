package test.api.tarifa.producto.exception;

import lombok.Data;
import test.api.tarifa.producto.enums.CommonError;

@Data
public class NotResultException extends RuntimeException {

  private final Error error;

  public NotResultException(Error error) {
    super(CommonError.NOT_EXIST_RESULT.getReasonPhrase());
    this.error = error;
  }
}
