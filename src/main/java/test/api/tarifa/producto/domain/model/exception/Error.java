package test.api.tarifa.producto.domain.model.exception;

import java.io.Serializable;

public interface Error extends Serializable {

  String getCode();

  String getMessage();

  int getValue();
}
