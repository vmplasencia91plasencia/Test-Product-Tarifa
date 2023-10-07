package test.api.tarifa.producto.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Response error", description = "Response when error")
public class ErrorResponseDTO {

  @ApiModelProperty(
      value = "Executed time request",
      example = "1696697669756")
  private Date timestamp;
  @ApiModelProperty(
      value = "Http status code",
      example = "1696697669756")
  private int status;
  @ApiModelProperty(
      value = "Error description",
      example = "1696697669756")
  private String error;
  @ApiModelProperty(
      value = "Path request",
      example = "/products/3547/brands/1")
  private String path;


}
