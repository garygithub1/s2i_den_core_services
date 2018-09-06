package ca.on.gov.s2i.denservice.client.model;

import java.util.List;

public class ResponseHeader {
    private String responseCode;
    private List<EsbMessage> ResponseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    

    public List<EsbMessage> getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(List<EsbMessage> responseMessage) {
        ResponseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "ResponseHeader [responseCode=" + responseCode + ", ResponseMessage=" + ResponseMessage + "]";
    }

}
