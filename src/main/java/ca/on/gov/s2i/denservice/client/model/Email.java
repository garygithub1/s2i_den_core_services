package ca.on.gov.s2i.denservice.client.model;

public class Email {
    private String emailType = "PRI";
    private String email;
    
    public String getEmailType() {
        return emailType;
    }
    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Email [emailType=" + emailType + ", email=" + email + "]";
    }
}
