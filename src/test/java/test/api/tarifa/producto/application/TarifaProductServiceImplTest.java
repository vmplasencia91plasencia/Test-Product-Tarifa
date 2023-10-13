package test.api.tarifa.producto.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.application.service.TarifaProductServiceImpl;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.domain.enums.Currency;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.domain.model.exception.NotResultException;
import test.api.tarifa.producto.infraestructure.adapter.mapper.ResponseMapper;
import test.api.tarifa.producto.infraestructure.adapter.repository.TarifaDboTransactional;
import test.api.tarifa.producto.util.JsonUtil;

@ExtendWith(MockitoExtension.class)
class TarifaProductServiceImplTest {


  @Mock
  private ResponseMapper mapper;

  @InjectMocks
  TarifaProductServiceImpl service;

  @Mock
  private TarifaDboTransactional transactional;

  TarifaProductServiceImplTest() {
  }

  @AfterEach
  void setUp() {
    this.service = new TarifaProductServiceImpl(transactional, mapper);
  }

  @Test
  void test_calcTarifaProducts_returnOK() throws IOException {

    ResponseDTO responseDTO = JsonUtil.stubFromJson("mapper/response_mapper_1.json", new TypeReference<>() {
    });
    LocalDateTime date = LocalDateTime.of(2020, Calendar.JULY, 14, 0, 0);
    when(transactional.getPriceApplyByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(getDomainEntity());
    when(mapper.toDto(any())).thenReturn(responseDTO);
    ResponseEntity<ResponseDTO> output = service.calcTarifaProducts("35455", "1", date);
    verify(transactional, times(1)).getPriceApplyByDateAndProductIdAndBrandId(any(), any(), any());
    assertNotNull(output);
  }

  @Test
  void test_calcTarifaProducts_returnNotResut() throws Exception {
    LocalDateTime date = LocalDateTime.of(2020, Calendar.JULY, 14, 0, 0);
    when(transactional.getPriceApplyByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(null);
    assertThrows(NotResultException.class, () -> {
      service.calcTarifaProducts("35455", "1", date);
    });
    verify(transactional, times(1)).getPriceApplyByDateAndProductIdAndBrandId(any(), any(), any());
  }

  private Price getDomainEntity() {
    return Price.builder()
        .price(new BigDecimal(100))
        .brandId("13")
        .startDate(LocalDateTime.of(2014, Calendar.FEBRUARY, 11, 0, 0))
        .endDate(LocalDateTime.of(2014, Calendar.FEBRUARY, 11, 0, 0))
        .priceList(1L)
        .priority(50)
        .productId("33")
        .currency(Currency.EUR)
        .build();
  }

}
