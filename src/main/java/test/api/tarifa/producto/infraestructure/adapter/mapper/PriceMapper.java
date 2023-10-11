package test.api.tarifa.producto.infraestructure.adapter.mapper;

import org.mapstruct.Mapper;
import test.api.tarifa.producto.domain.Price;
import test.api.tarifa.producto.infraestructure.adapter.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  Price toDomain(PriceEntity entity);

  PriceEntity toEntity(Price price);

}
