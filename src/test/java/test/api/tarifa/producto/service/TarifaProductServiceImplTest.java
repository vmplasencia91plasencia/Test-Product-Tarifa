package test.api.tarifa.producto.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.entity.Currency;
import test.api.tarifa.producto.entity.PriceEntity;
import test.api.tarifa.producto.exception.ExceptionHandlerCustom;
import test.api.tarifa.producto.exception.NotResultException;
import test.api.tarifa.producto.mapper.PriceMapper;
import test.api.tarifa.producto.repository.TarifaRepository;
import test.api.tarifa.producto.util.JsonUtil;

@ExtendWith(MockitoExtension.class)
class TarifaProductServiceImplTest {

  @Mock
  private TarifaRepository repository;
  @Mock
  private PriceMapper mapper;

  @Mock
  ExceptionHandlerCustom handlerCustom;

  @InjectMocks
  TarifaProductServiceImpl service;

  TarifaProductServiceImplTest() {
  }

  @AfterEach
  void setUp() {
    this.service = new TarifaProductServiceImpl(repository, mapper);
  }

  @Test
  void test_calcTarifaProducts_returnOK() throws IOException {

    ResponseDTO responseDTO = JsonUtil.stubFromJson("./mapper/price_mapper_1.json", new TypeReference<>() {
    });
    Date date = new GregorianCalendar(2020, Calendar.JULY, 14).getTime();
    when(repository.findTarifaByDateAndProductAndBrand(any(), any(), any())).thenReturn(Optional.of(getEntity()));
    when(mapper.toDto(any())).thenReturn(responseDTO);
    ResponseEntity<ResponseDTO> output = service.calcTarifaProducts("35455", "1", date);
    verify(repository, times(1)).findTarifaByDateAndProductAndBrand(any(), any(), any());
    assertNotNull(output);
  }

  @Test
  void test_calcTarifaProducts_returnNotResut() throws Exception {
    Date date = new GregorianCalendar(2020, Calendar.JULY, 14).getTime();
    when(repository.findTarifaByDateAndProductAndBrand(any(), any(), any())).thenReturn(Optional.empty());
    assertThrows(NotResultException.class, () -> {
      service.calcTarifaProducts("35455", "1", date);
    });
    verify(repository, times(1)).findTarifaByDateAndProductAndBrand(any(), any(), any());
  }

  private PriceEntity getEntity() {
    return PriceEntity.builder()
        .price(new BigDecimal(100))
        .brandId("13")
        .startDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
        .endDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime())
        .priceList(1L)
        .priority(50)
        .productId("33")
        .currency(Currency.EUR)
        .build();
  }

}
