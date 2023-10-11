package test.api.tarifa.producto.application.repository;

import java.util.Date;
import test.api.tarifa.producto.domain.Price;

public interface TarifaTransactional {

  Price getPriceApplyByDateAndProductIdAndBrandId(Date date, String productId, String brandId);
}
