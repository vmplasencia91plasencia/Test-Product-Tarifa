package test.api.tarifa.producto.infraestructure.rest.controller;

import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_BASE_PATH;
import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.FORMAT_DATE;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.application.usecases.TarifaProductService;

@RestController
@Validated
@RequestMapping(value = API_BASE_PATH)
public class ProductPriceControllerImpl implements ProductPriceController {

  private final TarifaProductService service;

  public ProductPriceControllerImpl(TarifaProductService service) {
    this.service = service;
  }

  @Override
  @GetMapping(value = "/{product_id}/brands/{brand_id}")
  public ResponseEntity<ResponseDTO> getProductPriceByFilters(
      @PathVariable(name = "product_id") @NotBlank String productId,
      @PathVariable(name = "brand_id") @NotBlank String brandId,
      @RequestParam(value = "date") @DateTimeFormat(pattern = FORMAT_DATE) @NotBlank Date date) {
    return service.calcTarifaProducts(productId, brandId, date);
  }
}
