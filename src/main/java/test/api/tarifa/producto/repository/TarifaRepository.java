package test.api.tarifa.producto.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.api.tarifa.producto.entity.PriceEntity;

@Repository
public interface TarifaRepository extends JpaRepository<PriceEntity,String> {

  @Query(value = "SELECT * FROM PRICES p "
      + "WHERE p.PRODUCT_ID = :product_id "
      + "AND p.BRAND_ID = :brand_id "
      + "AND TO_TIMESTAMP(:date ,'YYYY-MM-DD HH24:MI:SS') BETWEEN p.START_DATE AND p.END_DATE "
      + "ORDER BY p.PRIORITY DESC "
      + "fetch first 1 row only ",nativeQuery = true)
  Optional<PriceEntity> findTarifaByDateAndProductAndBrand(@Param("date") Date date,
      @Param("product_id") String productId,@Param("brand_id") String brandId);
}
