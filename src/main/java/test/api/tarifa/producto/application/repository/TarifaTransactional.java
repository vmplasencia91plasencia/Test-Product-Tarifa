package test.api.tarifa.producto.application.repository;

import java.time.LocalDateTime;
import test.api.tarifa.producto.domain.Price;

public interface TarifaTransactional {

  Price getPriceApplyByDateAndProductIdAndBrandId(LocalDateTime date, String productId, String brandId);
}
