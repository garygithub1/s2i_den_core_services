package ca.on.gov.s2i.denservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import ca.on.gov.common.config.SessionConfig;
import ca.on.gov.common.config.ws.WebServiceConfiguration;
import ca.on.gov.common.exception.CustomExceptionHandler;
import ca.on.gov.common.exception.ExceptionConfig;
import ca.on.gov.common.jwt.JwtConfig;
import ca.on.gov.common.jwt.RequestHeaderConfig;
import ca.on.gov.common.regex.RegexConfig;

@ComponentScan({"ca.on.gov.s2i.denservice"})
//@Import({ca.on.gov.s2i.common.components.impl.JwtCompImpl.class})
@SpringBootApplication
//@Qualifier("sessionConfig") 
@Import({CustomExceptionHandler.class, ExceptionConfig.class, JwtConfig.class, RequestHeaderConfig.class, RegexConfig.class, SessionConfig.class, WebServiceConfiguration.class})
//@ImportResource(locations= {"classpath:s2i-jwt-bean.xml"})
public class DenServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DenServiceApplication.class, args);
	}
}
