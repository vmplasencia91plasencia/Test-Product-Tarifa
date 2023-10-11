package test.api.tarifa.producto.infraestructure.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static test.api.tarifa.producto.util.JsonUtil.assertEqualsObjects;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.infraestructure.adapter.mapper.ResponseMapper;
import test.api.tarifa.producto.infraestructure.adapter.mapper.ResponseMapperImpl;
import test.api.tarifa.producto.util.JsonUtil;

@SpringBootTest(classes = ResponseMapperImpl.class)
class ResponseMapperTest extends AbstractMapper {

  @Autowired
  ResponseMapper responseMapper;

  private static final String ROOT_PATH = "./mapper/";

  @Test
  void test_map_toDto() throws IOException {
    ResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + "response_mapper_1.json", new TypeReference<>() {
    });
    ResponseDTO output = responseMapper.toDto(PRICE_DOMAIN);
    assertNotNull(output);
    assertNotNull(output.getPrice());
    assertNotNull(output.getProductId());
    assertEqualsObjects(expected, output);
  }

  @Test
  void test_map_toDto_wit_null() throws IOException {
    ResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + "response_mapper_2.json", new TypeReference<>() {
    });
    ResponseDTO output = responseMapper.toDto(PRICE_DOMAIN_WITH_NULL);
    assertNotNull(output);
    assertNull(output.getPrice());
    assertEqualsObjects(expected, output);
  }

  @Test
  void test_map_null() {
    assertDoesNotThrow(() -> {
      ResponseDTO output = responseMapper.toDto(null);
      assertNull(output);
    }, "Don't throw any Exception");
  }

}
