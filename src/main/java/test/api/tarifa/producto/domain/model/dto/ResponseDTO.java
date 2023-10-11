package test.api.tarifa.producto.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Response OK", description = "Response when request is OK and has data")
public class ResponseDTO {

  @ApiModelProperty(
      value = "Apply tariff",
      example = "1")
  @JsonProperty("tarifa_id")
  private Long priceList;

  @ApiModelProperty(
      value = "Brand identifier",
      example = "1(ZARA)")
  @JsonProperty("cadena_id")
  private String brandId;

  @ApiModelProperty(
      value = "Initial range for apply tariff",
      example = " 2020-06-14-00:00:00")
  @JsonProperty("start_date")
  private String startDate;

  @ApiModelProperty(
      value = "End range for apply tariff",
      example = " 2020-06-22-00:00:00")
  @JsonProperty("end_date")
  private String endDate;

  @ApiModelProperty(
      value = "Product identifier",
      example = " 2020-06-22-00:00:00")
  @JsonProperty("product_id")
  private String productId;

  @ApiModelProperty(
      value = "Product price",
      example = "22.50 EUR")
  @JsonProperty("price")
  private String price;


}
