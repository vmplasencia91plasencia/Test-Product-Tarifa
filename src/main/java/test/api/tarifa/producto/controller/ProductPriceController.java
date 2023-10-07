package test.api.tarifa.producto.controller;



import static test.api.tarifa.producto.constant.ConstantProductApi.API_TAG;
import static test.api.tarifa.producto.constant.ConstantProductApi.API_TITLE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import test.api.tarifa.producto.dto.ResponseDTO;
import test.api.tarifa.producto.exception.NotResultException;

@Api(value = API_TITLE, tags = {API_TAG})
public interface ProductPriceController {

  @ApiOperation(value = "Returns the price product", notes = "Returns the product list by filters")
  @ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "Response Ok", response = ResponseDTO.class),
      @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Product not found", response = NotResultException.class),
      @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request", response = NotResultException.class),
      @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error", response = NotResultException.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(name = "date", value = " Example: 2020-06-14-10.00.00"),
      @ApiImplicitParam(name = "productId", value = "35455", required = true),
      @ApiImplicitParam(name = "brandId", value = "Example: 1 (ZARA) ", required = true),
  })
  ResponseEntity<ResponseDTO> getProductPriceByFilters(String productId,String brandId, Date date);

}
