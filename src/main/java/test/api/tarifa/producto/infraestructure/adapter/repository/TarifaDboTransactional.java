package test.api.tarifa.producto.infraestructure.adapter.repository;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.api.tarifa.producto.application.repository.TarifaTransactional;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.domain.enums.CommonError;
import test.api.tarifa.producto.domain.model.exception.NotResultException;
import test.api.tarifa.producto.infraestructure.adapter.mapper.PriceMapper;

@Service
@Transactional(readOnly = true)
public class TarifaDboTransactional implements TarifaTransactional {

  private final TarifaRepository repository;
  private final PriceMapper priceMapper;

  public TarifaDboTransactional(TarifaRepository repository, PriceMapper priceMapper) {
    this.repository = repository;
    this.priceMapper = priceMapper;
  }

  @Override
  public Price getPriceApplyByDateAndProductIdAndBrandId(LocalDateTime date, String productId, String brandId) {
    return repository.findTarifaByDateAndProductAndBrand(date, productId, brandId)
        .stream().findFirst()
        .map(priceMapper::toDomain)
        .orElseThrow(() -> new NotResultException(CommonError.NOT_EXIST_RESULT));
  }
}
