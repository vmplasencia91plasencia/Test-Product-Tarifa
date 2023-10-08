package test.api.tarifa.producto.enums;

import test.api.tarifa.producto.exception.Error;

public enum CommonError implements Error {

  NOT_EXIST_RESULT("F-324", 324, "No se aplica ninguna tarifa");

  private final String code;
  private final int value;
  private final String reasonPhrase;

  CommonError(String code, int value, String reasonPhrase) {
    this.code = code;
    this.value = value;
    this.reasonPhrase = reasonPhrase;
  }

  public final String getReasonPhrase() {
    return this.reasonPhrase;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return this.reasonPhrase;
  }

  @Override
  public int getValue() {
    return this.value;
  }

}
