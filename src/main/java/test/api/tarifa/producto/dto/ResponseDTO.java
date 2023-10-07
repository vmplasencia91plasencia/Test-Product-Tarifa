package test.api.tarifa.producto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import test.api.tarifa.producto.entity.Currency;

@Data
@Builder
@ApiModel(value = "Response OK", description = "Response when request is OK and has data")
public class ResponseDTO {

  @JsonProperty("tarifa_id")
  private Long priceList;
  @JsonProperty("cadena_id")
  private String brandId;
  private String startDate;
  private String endDate;
  private String productId;
  @JsonProperty("price")
  private String currentPrice;
  @JsonIgnore
  private BigDecimal price;
  @JsonIgnore
  private Currency currency;

  public String getCurrentPrice() {
    if(price!=null){
      return price.toString().concat(" "+currency.name());
    }
    return null;
  }

}
