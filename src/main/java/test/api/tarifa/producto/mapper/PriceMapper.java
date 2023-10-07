package test.api.tarifa.producto.mapper;

import static test.api.tarifa.producto.constant.ConstantProductApi.FORMAT_DATE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  @Mapping(target="startDate", source = "startDate",dateFormat = FORMAT_DATE)
  @Mapping(target="endDate", source = "endDate", dateFormat = FORMAT_DATE)
  ResponseDTO toDto(PriceEntity entity);

}
