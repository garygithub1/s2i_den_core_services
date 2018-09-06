package ca.on.gov.s2i.denservice.client.model.s2iden;

import ca.on.gov.common.jwt.model.RequestHeader;

public class GetNotificationsReq {
    private RequestHeader requestHeader;
    
    private String email;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GetNotificationsReq [requestHeader=" + requestHeader + ", email=" + email + "]";
    }
    
}
