package ca.on.gov.s2i.denservice.client.model.s2iden;

import java.util.List;

import ca.on.gov.s2i.denservice.client.model.NotificationPref;
import ca.on.gov.s2i.denservice.client.model.ResponseHeader;

public class GetNotificationsRes {
    private ResponseHeader responseHeader;
    
    private List<NotificationPref> notificationPref;

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public List<NotificationPref> getNotificationPref() {
        return notificationPref;
    }

    public void setNotificationPref(List<NotificationPref> notificationPref) {
        this.notificationPref = notificationPref;
    }

    @Override
    public String toString() {
        return "GetNotificationsRes [responseHeader=" + responseHeader + ", notificationPref=" + notificationPref + "]";
    }
}
