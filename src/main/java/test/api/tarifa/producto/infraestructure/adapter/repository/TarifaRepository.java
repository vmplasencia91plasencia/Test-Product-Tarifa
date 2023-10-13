package test.api.tarifa.producto.infraestructure.adapter.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.api.tarifa.producto.infraestructure.adapter.entity.PriceEntity;

@Repository
public interface TarifaRepository extends JpaRepository<PriceEntity, String> {

  default List<PriceEntity> findTarifaByDateAndProductAndBrand(LocalDateTime date, String productId, String brandId) {
    return this.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date,
        date, productId, brandId);
  }

  List<PriceEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
      @Param("fromStartDate") LocalDateTime start, @Param("fromEndDate") LocalDateTime end,
      @Param("product_id") String productId, @Param("brand_id") String brandId);
}
