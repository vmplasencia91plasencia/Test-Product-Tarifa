package test.api.tarifa.producto.service;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.dto.ResponseDTO;

public interface TarifaProductService {

  ResponseEntity<ResponseDTO> calcTarifaProducts(String productId, String brandId, Date date);

}
