package test.api.tarifa.producto.infraestructure.rest.controller;


import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_TAG;
import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_TITLE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.domain.model.dto.ResponseDTO;
import test.api.tarifa.producto.domain.model.exception.NotResultException;

@Api(value = API_TITLE, tags = {API_TAG})
public interface ProductPriceController {

  @ApiOperation(value = "Returns the price product", notes = "Returns the product list by filters")
  @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "Response Ok", response = ResponseDTO.class),
      @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Product not found", response = NotFoundException.class),
      @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Ba", response = NotResultException.class),
  })
  ResponseEntity<ResponseDTO> getProductPriceByFilters(@ApiParam(name = "productId", value = "35455", required =
      true) String productId,
      @ApiParam(name = "brandId", value = "Example: 1 (ZARA) ", required = true) String brandId,
      @ApiParam(name = "date", value = " Example: 2020-06-14-10:00:00") LocalDateTime date);

}
