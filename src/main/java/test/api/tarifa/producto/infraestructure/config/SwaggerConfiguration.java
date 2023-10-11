package test.api.tarifa.producto.infraestructure.config;

import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_DESCRIPTION;
import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_TITLE;
import static test.api.tarifa.producto.domain.model.constant.ConstantProductApi.API_VERSION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("test.api.tarifa.producto.infraestructure.rest.controller"))
        .paths(PathSelectors.any())
        .build().apiInfo(apiInfo());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/swagger-ui.html#");
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(API_TITLE)
        .description(API_DESCRIPTION)
        .version(API_VERSION)
        .build();
  }

}
