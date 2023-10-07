package test.api.tarifa.producto.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PRICES")
@Data
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRICE_LIST", unique = true, nullable = false)
  private Long priceList;

  @Column(name = "BRAND_ID", nullable = false, length = 36)
  private String brandId;

  @Column(name = "START_DATE", nullable = false)
  private Date startDate;

  @Column(name = "END_DATE", nullable = false)
  private Date endDate;

  @Column(name = "PRODUCT_ID", nullable = false, length = 36)
  private String productId;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE",nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(name = "CURR")
  private Currency currency;

}
