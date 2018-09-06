package ca.on.gov.s2i.denservice.client.model.s2iden;

import ca.on.gov.common.jwt.model.RequestHeader;

public class ActiveClientNotificationReq {
    private RequestHeader requestHeader;
    
    private String pid;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "ActiveClientNotificationReq [requestHeader=" + requestHeader + ", pid=" + pid + "]";
    }

}
