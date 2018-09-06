package ca.on.gov.s2i.denservice.client;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class EsbErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        //like 500 error
        //throw org.springframework.web.client.HttpServerErrorException
        System.out.println("some error in rest:" + response);
        super.handleError(response);
 
    }
}