package ca.on.gov.s2i.denservice.client.model;

public class RequestHeader {
    private String transactionId;
    private String timestamp;
    private String serviceProviderCode;
    private String channelId;
    private String language;
    private String terminalId;
    private String internetIPAddress;
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getServiceProviderCode() {
        return serviceProviderCode;
    }
    public void setServiceProviderCode(String serviceProviderCode) {
        this.serviceProviderCode = serviceProviderCode;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getTerminalId() {
        return terminalId;
    }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
    public String getInternetIPAddress() {
        return internetIPAddress;
    }
    public void setInternetIPAddress(String internetIPAddress) {
        this.internetIPAddress = internetIPAddress;
    }
    
    @Override
    public String toString() {
        return "RequestHeader [transactionId=" + transactionId + ", timestamp=" + timestamp + ", serviceProviderCode="
                + serviceProviderCode + ", channelId=" + channelId + ", language=" + language + ", terminalId="
                + terminalId + ", internetIPAddress=" + internetIPAddress + "]";
    }
    
}
