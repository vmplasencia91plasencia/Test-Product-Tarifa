package test.api.tarifa.producto.infraestructure.mapper;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.domain.enums.Currency;
import test.api.tarifa.producto.infraestructure.adapter.entity.PriceEntity;

public abstract class AbstractMapper {

  public static Price PRICE_DOMAIN = Price.builder()
      .price(new BigDecimal(100))
      .brandId("13")
      .startDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
      .endDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
      .priceList(1L)
      .priority(50)
      .productId("33")
      .currency(Currency.EUR)
      .build();

  public static Price PRICE_DOMAIN_WITH_NULL = Price.builder()
      .brandId("13")
      .startDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
      .priceList(1L)
      .priority(50)
      .productId("33")
      .currency(Currency.EUR)
      .build();

  public static PriceEntity PRICE_ENTITY = PriceEntity.builder()
      .price(new BigDecimal(100))
      .brandId("22")
      .startDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 5).getTime())
      .endDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 22).getTime())
      .priceList(1L)
      .priority(10)
      .productId("33")
      .currency(Currency.EUR)
      .build();

  public static PriceEntity PRICE_ENTITY_WITH_NULL = PriceEntity.builder()
      .price(new BigDecimal(100))
      .brandId("33")
      .startDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 25).getTime())
      .priceList(1L)
      .priority(50)
      .productId("33")
      .currency(Currency.EUR)
      .build();


}
