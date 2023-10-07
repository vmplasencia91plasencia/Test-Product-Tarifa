package test.api.tarifa.producto.exception;

import java.io.Serializable;
public interface Error extends Serializable {

  String getCode();
  String getMessage();

   int  getValue();

  default int getHttpStatus() {
    return this.getValue();
  }
}
