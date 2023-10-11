package test.api.tarifa.producto.application.service;

import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.FORMAT_DATE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.infraestructure.adapter.mapper.ResponseMapper;
import test.api.tarifa.producto.infraestructure.adapter.repository.TarifaDboTransactional;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.domain.enums.CommonError;
import test.api.tarifa.producto.domain.model.exception.NotResultException;
import test.api.tarifa.producto.application.usecases.TarifaProductService;

@Service
@Slf4j
public class TarifaProductServiceImpl implements TarifaProductService {

  private final TarifaDboTransactional tarifaDboTransactional;
  private final ResponseMapper mapper;

  public TarifaProductServiceImpl(TarifaDboTransactional tarifaDboTransactional, ResponseMapper mapper) {
    this.tarifaDboTransactional = tarifaDboTransactional;
    this.mapper = mapper;
  }

  @Override
  public ResponseEntity<ResponseDTO> calcTarifaProducts(String productId, String brandId, Date date) {
    log.info("Initial request with product_id: {}, brand_id: {} and date: {} ", productId, brandId,
        new SimpleDateFormat(FORMAT_DATE).format(date));

    if (ObjectUtils.allNotNull(productId, brandId, date)) {
      Optional<Price> price = Optional.ofNullable(tarifaDboTransactional.getPriceApplyByDateAndProductIdAndBrandId(date,
          productId
          , brandId));
      if (price.isPresent()) {
        ResponseDTO responseDTO = mapper.toDto(price.get());
        log.info("Successful response: {}", responseDTO.toString());
        return ResponseEntity.ok(responseDTO);
      }
    }
    throw new NotResultException(CommonError.NOT_EXIST_RESULT);
  }

}
