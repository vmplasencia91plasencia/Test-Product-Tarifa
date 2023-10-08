package test.api.tarifa.producto.mapper;

import static test.api.tarifa.producto.constant.ConstantProductApi.FORMAT_DATE;

import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  @Mapping(target = "startDate", source = "startDate", dateFormat = FORMAT_DATE)
  @Mapping(target = "endDate", source = "endDate", dateFormat = FORMAT_DATE)
  @Mapping(target = "price",expression = "java(getCurrentPrice(entity))")
  ResponseDTO toDto(PriceEntity entity);

  default String getCurrentPrice(PriceEntity entity) {
    if (ObjectUtils.allNotNull(entity.getPrice(),entity.getCurrency())) {
      return entity.getPrice().toString().concat(" " + entity.getCurrency().name());
    }
    return null;
  }
}
