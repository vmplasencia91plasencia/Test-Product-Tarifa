package test.api.tarifa.producto.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import test.api.tarifa.producto.dto.ErrorResponseDTO;
import test.api.tarifa.producto.enums.CommonError;

@SpringBootTest(classes = ExceptionHandlerCustom.class)
class ExceptionHandlerCustomTest {

  @Autowired
  ExceptionHandlerCustom handlerCustom;


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
