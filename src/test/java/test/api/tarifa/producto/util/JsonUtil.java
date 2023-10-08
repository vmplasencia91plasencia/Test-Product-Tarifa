package test.api.tarifa.producto.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;

public class JsonUtil {

  public static <T> T stubFromJson(String resourceName, TypeReference<T> valueType) throws IOException {
    return getObjectMapper().readValue(getResourceStream(resourceName), valueType);
  }

  private static ObjectMapper getObjectMapper() {
    return new ObjectMapper()
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
  }

  public static InputStream getResourceStream(String resourceName) {
    return JsonUtil.class.getClassLoader().getResourceAsStream(resourceName);
  }

  public static String generateJson(Object object) throws JsonProcessingException {
    return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
  }

  public static void assertEqualsObjects(Object expected, Object actual) throws JsonProcessingException {
    if (!expected.equals(actual)) {
      String expectedJson = JsonUtil.generateJson(expected);
      String actualJson = JsonUtil.generateJson(actual);
      Assertions.assertEquals(expectedJson, actualJson);
    }
  }

}
