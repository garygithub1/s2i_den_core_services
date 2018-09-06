package ca.on.gov.s2i.denservice.client;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

// extends RestTemplate
@Component
public class EsbRestTemplate extends RestTemplate {
    
    static Pattern exludeParameterPattern = Pattern.compile( "^[A-Z]{2,}");
    
    public EsbRestTemplate() {

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();        
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.UpperCamelCaseStrategy() {
            
            private static final long serialVersionUID = 7510858422840665802L;
            
            @Override
            public String nameForField(MapperConfig<?> config,  AnnotatedField field, String defaultName) {
                JsonProperty annot = field.getAnnotation(JsonProperty.class);
                if(annot != null){
                    return annot.value();
                }
                return super.nameForField(config, field, defaultName);
            }

            @Override
            public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                JsonProperty annot = method.getAnnotation(JsonProperty.class);
                if(annot != null){
                    return annot.value();
                }
                return super.nameForGetterMethod(config, method, defaultName);
            }
            
            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                JsonProperty annot = method.getAnnotation(JsonProperty.class);
                if(annot != null){
                    return annot.value();
                }
                return super.nameForGetterMethod(config, method, defaultName);
            }

            @Override
            public String translate(String input) {
                Matcher matcher = exludeParameterPattern.matcher(input);
                if(matcher.matches()){
                    return input;
                }else if("odlr".equalsIgnoreCase(input)){
                    return "ODLR";
                }else if("rin".equalsIgnoreCase(input)){
                    return "RIN";
                }
                String result = super.translate(input);
                
                return result;
            }

        });
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        jsonMessageConverter.setObjectMapper(objectMapper);

        //application/octet-stream
        // "text", "html"
        List<MediaType> types = Arrays.asList(
                new MediaType("application", "octet-stream", StandardCharsets.UTF_8),
                new MediaType("application", "json"),
                //new MediaType("application", "*"),
                new MediaType("application", "*+json")
        );
        //new MediaType("application", "octet-stream", StandardCharsets.UTF_8) is for jdk7
        //new MediaType("application", "octet-stream") is for jdk6
        jsonMessageConverter.setSupportedMediaTypes(types);

        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(jsonMessageConverter);
        this.setMessageConverters(messageConverters);

        this.setErrorHandler(new EsbErrorHandler());
        
    }

    public EsbRestTemplate(ClientHttpRequestFactory requestFactory) {
        this();
        setRequestFactory(requestFactory);
    }

    /**
     * Property naming strategy that converts both ways between camelCase and under_score
     * property names.
     */
}
