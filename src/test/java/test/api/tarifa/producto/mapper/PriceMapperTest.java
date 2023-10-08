package test.api.tarifa.producto.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static test.api.tarifa.producto.util.JsonUtil.assertEqualsObjects;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.entity.Currency;
import test.api.tarifa.producto.entity.PriceEntity;
import test.api.tarifa.producto.util.JsonUtil;

@SpringBootTest(classes = PriceMapperImpl.class)
class PriceMapperTest {

  @Autowired
  PriceMapper priceMapper;

  private static final String ROOT_PATH = "./mapper/";

  @Test
  void test_PriceMapper() throws IOException {
    ResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_1.json", new TypeReference<>() {
    });
    PriceEntity priceEntity = PriceEntity.builder()
        .price(new BigDecimal(100))
        .brandId("13")
        .startDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
        .endDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
        .priceList(1L)
        .priority(50)
        .productId("33")
        .currency(Currency.EUR)
        .build();
    ResponseDTO output = priceMapper.toDto(priceEntity);
    assertNotNull(output);
    assertNotNull(output.getPrice());
    assertNotNull(output.getProductId());
    assertEqualsObjects(expected, output);
  }

  @Test
  void test_PriceMapper_whenNullValues() throws IOException {
    ResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_2.json", new TypeReference<>() {
    });
    PriceEntity priceEntity = PriceEntity.builder()
        .brandId("13")
        .startDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
        .priceList(1L)
        .priority(50)
        .productId("33")
        .currency(Currency.EUR)
        .build();
    ResponseDTO output = priceMapper.toDto(priceEntity);
    assertNotNull(output);
    assertNull(output.getPrice());
    assertEqualsObjects(expected, output);
  }

}
