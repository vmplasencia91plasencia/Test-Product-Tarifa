package test.api.tarifa.producto.infraestructure.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.infraestructure.adapter.entity.PriceEntity;
import test.api.tarifa.producto.infraestructure.adapter.mapper.PriceMapper;
import test.api.tarifa.producto.infraestructure.adapter.mapper.PriceMapperImpl;
import test.api.tarifa.producto.util.JsonUtil;

@SpringBootTest(classes = PriceMapperImpl.class)
class PriceMapperTest extends AbstractMapper {

  @Autowired
  PriceMapper priceMapper;

  private static final String ROOT_PATH = "./mapper/";

  @Test
  void test_mapToDomain() throws IOException {
    Price expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_1.json", new TypeReference<>() {
    });
    Price output = priceMapper.toDomain(PRICE_ENTITY);
    assertNotNull(output);
    assertNotNull(output.getPrice());
    assertNotNull(output.getProductId());
    JsonUtil.assertEqualsObjects(expected, output);
  }

  @Test
  void test_mapToEntity() throws IOException {
    PriceEntity expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_2.json", new TypeReference<>() {
    });
    PriceEntity output = priceMapper.toEntity(PRICE_DOMAIN);
    assertNotNull(output);
    assertNotNull(output.getPrice());
    JsonUtil.assertEqualsObjects(expected, output);
  }

  @Test
  void test_mapToDomain_with_null() throws IOException {
    Price expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_3.json", new TypeReference<>() {
    });
    Price output = priceMapper.toDomain(PRICE_ENTITY_WITH_NULL);
    assertNotNull(output);
    JsonUtil.assertEqualsObjects(expected, output);
  }

  @Test
  void test_mapToEntity_with_null() throws IOException {
    PriceEntity expected = JsonUtil.stubFromJson(ROOT_PATH + "price_mapper_4.json", new TypeReference<>() {
    });
    PriceEntity output = priceMapper.toEntity(PRICE_DOMAIN_WITH_NULL);
    assertNotNull(output);
    JsonUtil.assertEqualsObjects(expected, output);
  }

  @Test
  void test_map_null() {
    assertDoesNotThrow(() -> {
      assertNull(priceMapper.toEntity(null));
    }, "Don't throw any Exception");
  }

}
