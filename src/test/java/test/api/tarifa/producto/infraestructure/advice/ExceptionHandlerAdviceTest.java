package test.api.tarifa.producto.infraestructure.advice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import test.api.tarifa.producto.domain.model.dto.ErrorResponseDTO;
import test.api.tarifa.producto.domain.enums.CommonError;
import test.api.tarifa.producto.domain.model.exception.NotResultException;
import test.api.tarifa.producto.infraestructure.rest.advice.ExceptionHandlerAdvice;

@SpringBootTest(classes = ExceptionHandlerAdvice.class)
class ExceptionHandlerAdviceTest {

  @Autowired
  ExceptionHandlerAdvice handlerCustom;


  @Test
  void test_handleThorwErrorOK() {
    MockHttpServletRequest servletRequest = new MockHttpServletRequest();
    WebRequest request = new ServletWebRequest(servletRequest);
    servletRequest.setRequestURI("/v1/someuri");
    NotResultException notResultException = new NotResultException(CommonError.NOT_EXIST_RESULT);
    ResponseEntity<Object> response = handlerCustom.handleThorwError(notResultException, request);
    assertNotNull(response);
    assertNotNull(((ErrorResponseDTO) Objects.requireNonNull(response.getBody())).getError());
  }

}
