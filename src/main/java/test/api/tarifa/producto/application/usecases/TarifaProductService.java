package test.api.tarifa.producto.application.usecases;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;

public interface TarifaProductService {

  ResponseEntity<ResponseDTO> calcTarifaProducts(String productId, String brandId, Date date);

}
