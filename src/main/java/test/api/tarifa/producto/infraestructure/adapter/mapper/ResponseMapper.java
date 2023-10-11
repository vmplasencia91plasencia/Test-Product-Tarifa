package test.api.tarifa.producto.infraestructure.adapter.mapper;

import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.FORMAT_DATE;

import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

  @Mapping(target = "startDate", source = "startDate", dateFormat = FORMAT_DATE)
  @Mapping(target = "endDate", source = "endDate", dateFormat = FORMAT_DATE)
  @Mapping(target = "price", expression = "java(getCurrentPrice(price))")
  ResponseDTO toDto(Price price);

  default String getCurrentPrice(Price entity) {
    if (ObjectUtils.allNotNull(entity.getPrice(), entity.getCurrency())) {
      return entity.getPrice().toString().concat(" " + entity.getCurrency().name());
    }
    return null;
  }
}
