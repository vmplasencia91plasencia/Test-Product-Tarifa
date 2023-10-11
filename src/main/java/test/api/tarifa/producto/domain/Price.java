package test.api.tarifa.producto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import test.api.tarifa.producto.domain.enums.Currency;

@Data
@Builder
@AllArgsConstructor
public class Price {

  private Long priceList;

  private String brandId;

  private Date startDate;

  private Date endDate;

  private String productId;

  private Integer priority;

  private BigDecimal price;

  private Currency currency;

  public Price() {
  }

}
