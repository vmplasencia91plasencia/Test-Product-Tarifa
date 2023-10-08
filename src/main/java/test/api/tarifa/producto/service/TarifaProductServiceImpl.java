package test.api.tarifa.producto.service;

import static test.api.tarifa.producto.constant.ConstantProductApi.FORMAT_DATE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.entity.PriceEntity;
import test.api.tarifa.producto.enums.CommonError;
import test.api.tarifa.producto.exception.NotResultException;
import test.api.tarifa.producto.mapper.PriceMapper;
import test.api.tarifa.producto.repository.TarifaRepository;

@Service
@Slf4j
public class TarifaProductServiceImpl implements TarifaProductService {

  private final TarifaRepository repository;
  private final PriceMapper mapper;

  public TarifaProductServiceImpl(TarifaRepository repository, PriceMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public ResponseEntity<ResponseDTO> calcTarifaProducts(String productId, String brandId, Date date) {
    log.info("Initial request with product_id: {}, brand_id: {} and date: {} ", productId, brandId,
        new SimpleDateFormat(FORMAT_DATE).format(date));

    if (ObjectUtils.allNotNull(productId, brandId, date)) {
      Optional<PriceEntity> priceEntity = repository.findTarifaByDateAndProductAndBrand(date, productId, brandId);
      if (priceEntity.isPresent()) {
        ResponseDTO responseDTO = mapper.toDto(priceEntity.get());
        log.info("Successful response: {}", responseDTO.toString());
        return ResponseEntity.ok(responseDTO);
      }
    }
    throw new NotResultException(CommonError.NOT_EXIST_RESULT);
  }

}
