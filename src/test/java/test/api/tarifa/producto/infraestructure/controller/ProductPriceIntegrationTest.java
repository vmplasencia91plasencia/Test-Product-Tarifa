package test.api.tarifa.producto.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import test.api.tarifa.producto.ProductAppApplication;
import test.api.tarifa.producto.infraestructure.rest.controller.ProductPriceControllerImpl;
import test.api.tarifa.producto.domain.model.dto.ErrorResponseDTO;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.util.JsonUtil;

@SpringBootTest(classes = {ProductAppApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
class ProductPriceIntegrationTest {

  @Autowired
  private WebTestClient webClient;

  @Autowired
  ProductPriceControllerImpl controller;

  private static final String ROOT_PATH = "./integration/";

  @AfterEach
  void setUp() {
    this.webClient = WebTestClient.bindToController(controller).build();
  }

  private static Stream<Arguments> provideInputForResponseOk() {
    return Stream.of(
        Arguments.of("Test 1", "2020-06-14-10:00:00", "35455", "1", "ok/test1_expected.json"),
        Arguments.of("Test 2", "2020-06-14-16:00:00", "35455", "1", "ok/test2_expected.json"),
        Arguments.of("Test 3", "2020-06-14-21:00:00", "35455", "1", "ok/test3_expected.json"),
        Arguments.of("Test 4", "2020-06-15-10:00:00", "35455", "1", "ok/test4_expected.json"),
        Arguments.of("Test 5", "2020-06-16-21:00:00", "35455", "1", "ok/test5_expected.json")
    );
  }

  private static Stream<Arguments> provideInputForResponseErrorXXX() {
    return Stream.of(
        Arguments.of("Test 1 Not found ", "/products/777", "error/not_found-1_expected.json"),
        Arguments.of("Test 2 Not found", "/products/35455/brands/1/mmm", "error/not_found-2_expected.json"),
        Arguments.of("Test 3 Not found", "/products/35455/brands/1?date=2020-06-16-21.00.00", Strings.EMPTY),
        Arguments.of("Test 4 Not result ", "/products/354557777/brands/13?date=2020-06-16-21:00:00",
            "error/not_result_expected.json")
    );
  }

  @ParameterizedTest
  @MethodSource("provideInputForResponseOk")
  void test_whenDayAndProductIdAndBrandId_thenReturnOK(String description, String date, String productId,
      String brandId, String file) throws IOException {
    ResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + file, new TypeReference<>() {
    });
    assertDoesNotThrow(() -> {
      ResponseDTO output = getProductPriceByFilters(date, productId, brandId);
      assertAll(expected, output);
    });
  }

  @ParameterizedTest
  @MethodSource("provideInputForResponseErrorXXX")
  void test_whenDayAndProductIdAndBrandId_thenReturnError(String description, String uri, String file)
      throws IOException {
    AtomicReference<ErrorResponseDTO> output = new AtomicReference<>();
    output.set(getProductPriceErrors(uri));
    if (StringUtils.isNotBlank(file)) {
      ErrorResponseDTO expected = JsonUtil.stubFromJson(ROOT_PATH + file, new TypeReference<>() {
      });
      assertAllError(expected, output.get());
    }


  }

  private ResponseDTO getProductPriceByFilters(String date, String productId, String brandId) {
    return webClient
        .get()
        .uri("/products/" + productId + "/brands/" + brandId + "?date=" + date)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(new ParameterizedTypeReference<ResponseDTO>() {
        })
        .returnResult().getResponseBody();
  }

  private ErrorResponseDTO getProductPriceErrors(String uri) {
    return webClient
        .get()
        .uri(uri)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .is4xxClientError()
        .expectBody(new ParameterizedTypeReference<ErrorResponseDTO>() {
        })
        .returnResult().getResponseBody();
  }

  private void assertAll(ResponseDTO expected, ResponseDTO output) {
    Assertions.assertAll(
        () -> assertNotNull(output),
        () -> assertEquals(expected.getProductId(), output.getProductId()),
        () -> assertEquals(expected.getPrice(), output.getPrice()),
        () -> assertEquals(expected.getBrandId(), output.getBrandId()),
        () -> assertEquals(expected.getPriceList(), output.getPriceList())
    );
  }

  private void assertAllError(ErrorResponseDTO expected, ErrorResponseDTO output) {
    Assertions.assertAll(
        () -> assertNotNull(output),
        () -> assertEquals(expected.getError(), output.getError()),
        () -> assertEquals(expected.getPath(), output.getPath()),
        () -> assertEquals(expected.getStatus(), output.getStatus())
    );
  }

}
