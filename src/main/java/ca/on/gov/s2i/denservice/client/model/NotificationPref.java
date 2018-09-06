package ca.on.gov.s2i.denservice.client.model;

public class NotificationPref {
    private String type;
    private String enrolled;
    private String status;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getEnrolled() {
        return enrolled;
    }
    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "NotificationPref [type=" + type + ", enrolled=" + enrolled + ", status=" + status + "]";
    }
    
}
