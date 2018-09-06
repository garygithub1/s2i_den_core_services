package ca.on.gov.s2i.denservice.client.model.s2iden;

import ca.on.gov.s2i.denservice.client.model.ResponseHeader;

public class GetClientNotificationRes {
    private ResponseHeader responseHeader;
    
    private String clientName;
    
    private String status;
    
    private String pid;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "GetClientNotificationRes [responseHeader=" + responseHeader + ", clientName=" + clientName + ", status="
                + status + ", pid=" + pid + "]";
    }

}
