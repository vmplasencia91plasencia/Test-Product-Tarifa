package test.api.tarifa.producto.infraestructure.adapter.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import test.api.tarifa.producto.domain.enums.Currency;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRICE_LIST", unique = true, nullable = false)
  private Long priceList;

  @Column(name = "BRAND_ID", nullable = false, length = 36)
  private String brandId;

  @Column(name = "START_DATE", nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime endDate;

  @Column(name = "PRODUCT_ID", nullable = false, length = 36)
  private String productId;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE", nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(name = "CURR")
  private Currency currency;

  public PriceEntity() {
  }

}
