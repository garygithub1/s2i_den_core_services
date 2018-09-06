package ca.on.gov.s2i.denservice.client.model;

public class ProfileInfo {
    private String firstName;
    private String lastName;
    private String langPref;
    private String receiveNews = "false";
    private String remindSchedule = "30";
    private String clientType = "DEN";
    
    private Email email;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLangPref() {
        return langPref;
    }
    public void setLangPref(String langPref) {
        this.langPref = langPref;
    }
    public String getReceiveNews() {
        return receiveNews;
    }
    public void setReceiveNews(String receiveNews) {
        this.receiveNews = receiveNews;
    }
    public String getRemindSchedule() {
        return remindSchedule;
    }
    public void setRemindSchedule(String remindSchedule) {
        this.remindSchedule = remindSchedule;
    }
    public String getClientType() {
        return clientType;
    }
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "ProfileInfo [firstName=" + firstName + ", lastName=" + lastName + ", langPref=" + langPref
                + ", receiveNews=" + receiveNews + ", remindSchedule=" + remindSchedule + ", clientType=" + clientType
                + ", email=" + email + "]";
    }
}
