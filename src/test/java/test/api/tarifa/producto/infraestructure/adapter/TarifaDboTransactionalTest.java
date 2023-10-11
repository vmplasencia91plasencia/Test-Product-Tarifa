package test.api.tarifa.producto.infraestructure.adapter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.domain.model.exception.NotResultException;
import test.api.tarifa.producto.infraestructure.adapter.entity.PriceEntity;
import test.api.tarifa.producto.infraestructure.adapter.mapper.PriceMapper;
import test.api.tarifa.producto.infraestructure.adapter.repository.TarifaDboTransactional;
import test.api.tarifa.producto.infraestructure.adapter.repository.TarifaRepository;
import test.api.tarifa.producto.util.JsonUtil;

@ExtendWith(MockitoExtension.class)
class TarifaDboTransactionalTest {

  @Mock
  TarifaRepository tarifaRepository;

  @Mock
  PriceMapper mapper;

  @InjectMocks
  TarifaDboTransactional transactional;

  @BeforeEach
  void setUp() {
    transactional = new TarifaDboTransactional(tarifaRepository, mapper);
  }

  @Test
  void test_getPriceApplyOk() {
    assertDoesNotThrow(() -> {
      PriceEntity entity = JsonUtil.stubFromJson("./mapper/price_mapper_2.json", new TypeReference<>() {
      });
      Mockito.when(tarifaRepository.findTarifaByDateAndProductAndBrand(any(), any(), any()))
          .thenReturn(Optional.of(entity));
      Mockito.when(mapper.toDomain(any())).thenReturn(Price.builder().build());
      Price output = transactional.getPriceApplyByDateAndProductIdAndBrandId(new GregorianCalendar(2020,
          Calendar.JULY, 14).getTime(), "45", "33");
      verify(tarifaRepository, times(1)).findTarifaByDateAndProductAndBrand(any(), any(), any());
      verify(mapper, times(1)).toDomain(any());
      assertNotNull(output);
    });
  }

  @Test
  void test_getPriceApplyWithMapperThrowException() {
    assertThrows(NotResultException.class, () -> {
      Mockito.when(tarifaRepository.findTarifaByDateAndProductAndBrand(any(), any(), any()))
          .thenReturn(Optional.empty());
      transactional.getPriceApplyByDateAndProductIdAndBrandId(new GregorianCalendar(2020,
          Calendar.JULY, 14).getTime(), "45", "33");
      verify(transactional, times(1)).getPriceApplyByDateAndProductIdAndBrandId(any(), any(), any());
      verify(mapper, times(0)).toDomain(any());
    });
  }

}
