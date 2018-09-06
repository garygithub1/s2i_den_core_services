package ca.on.gov.s2i.denservice.client;

import ca.on.gov.s2i.denservice.client.model.s2iden.ActiveClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationRes;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsRes;

public interface S2iDenService {
    public GetNotificationsRes getNotifications(GetNotificationsReq getNotificationsReq);
    
    public GetClientNotificationRes getClientNotification(GetClientNotificationReq getNotificationsReq);

    public GetClientNotificationRes addClientNotification(GetClientNotificationReq getNotificationsReq);

    public void activeClientNotification(ActiveClientNotificationReq activeClientNotificationReq);

    public void deleteClientNotification(ActiveClientNotificationReq activeClientNotificationReq);
}
