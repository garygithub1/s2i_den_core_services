package ca.on.gov.s2i.denservice.client.model.s2iden;

import ca.on.gov.common.jwt.model.RequestHeader;

public class GetClientNotificationReq {
    private RequestHeader requestHeader;

    private String notificationType;
    private String driverLicenceNumber;
    private String postCode;
    private String plateNumber;
    private String rin;
    
    private ca.on.gov.s2i.denservice.client.model.ProfileInfo profileInfo;
    private String pid;
    
    public RequestHeader getRequestHeader() {
        return requestHeader;
    }
    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
    public String getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }
    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public String getRin() {
        return rin;
    }
    public void setRin(String rin) {
        this.rin = rin;
    }
    public ca.on.gov.s2i.denservice.client.model.ProfileInfo getProfileInfo() {
        return profileInfo;
    }
    public void setProfileInfo(ca.on.gov.s2i.denservice.client.model.ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    @Override
    public String toString() {
        return "GetClientNotificationReq [requestHeader=" + requestHeader + ", notificationType=" + notificationType
                + ", driverLicenceNumber=" + driverLicenceNumber + ", postCode=" + postCode + ", plateNumber="
                + plateNumber + ", rin=" + rin + ", profileInfo=" + profileInfo + ", pid=" + pid + "]";
    }
    
}
