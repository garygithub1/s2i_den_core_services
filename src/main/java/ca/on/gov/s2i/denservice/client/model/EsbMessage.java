package ca.on.gov.s2i.denservice.client.model;

public class EsbMessage {
    private String messageCode;
    private String logMessage;
    
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    public String getLogMessage() {
        return logMessage;
    }
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
    
    @Override
    public String toString() {
        return "EsbMessage [messageCode=" + messageCode + ", logMessage=" + logMessage + "]";
    }
    
}
