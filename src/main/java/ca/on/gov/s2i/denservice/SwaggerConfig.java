package ca.on.gov.s2i.denservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        //.paths(PathSelectors.regex("/v1/.*"))
        //.paths(PathSelectors.any())

        //|/token|/publicKey|/question/.*|/verification|/verification/.*|/card|/card/.*|/citizen|/wallet|/wallet/.*|/person/.*|/device|/device/.*
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
    }
}