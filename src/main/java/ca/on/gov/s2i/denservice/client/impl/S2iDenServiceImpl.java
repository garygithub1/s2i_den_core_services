package ca.on.gov.s2i.denservice.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ca.on.gov.s2i.denservice.client.EsbRestTemplate;
import ca.on.gov.s2i.denservice.client.S2iDenService;
import ca.on.gov.s2i.denservice.client.model.s2iden.ActiveClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationRes;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsRes;

@Service
public class S2iDenServiceImpl implements S2iDenService {
    private static final Logger logger = LoggerFactory.getLogger(S2iDenServiceImpl.class);
    
    //EsbRestTemplate esbRestTemplate = new EsbRestTemplate();
    @Autowired
    EsbRestTemplate esbRestTemplate;

    @Override
    public GetClientNotificationRes getClientNotification(GetClientNotificationReq getNotificationsReq) {
        String url = "http://10.160.200.144:9080/S2I_EnotificationModuleWeb/S2iDENServiceHTTPExport/GetClientNotification";
        //url = env.getProperty("esb.ohcdlr.verify.url");
        //url = LogUitl.getInstance().envGetProperty(env, "esb.ohcdlr.verify.url");
        
        //String result = esbRestTemplate.postForObject(url, String.class, args);
        
        HttpHeaders headers = new HttpHeaders();
        //headers.set("TargetFunctionName", "GetClientNotification");
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        //getNotificationsReq.setDriverLicenceNumber("fdsa");
        
        HttpEntity<GetClientNotificationReq> entity = new HttpEntity<>(getNotificationsReq, headers);
        
        //String result = esbRestTemplate.postForObject(url, null, String.class);
        //logger.warn(result);

        //ResponseEntity<GetNotificationsRes> responseEntity = esbRestTemplate.postForEntity(url, entity, GetNotificationsRes.class);
        ResponseEntity<GetClientNotificationRes> responseEntity = esbRestTemplate.postForEntity(url, entity, GetClientNotificationRes.class);

        //validRes(responseEntity);
        logger.info(responseEntity.getBody().toString());

        return responseEntity.getBody();
        //return null;
    }

    @Override
    public GetClientNotificationRes addClientNotification(GetClientNotificationReq getNotificationsReq) {
        String url = "http://10.160.200.144:9080/S2I_EnotificationModuleWeb/S2iDENServiceHTTPExport/AddClientNotification";

        //logger.info(esbRestTemplate.postForObject(url, getNotificationsReq, String.class));
        
        ResponseEntity<GetClientNotificationRes> responseEntity = esbRestTemplate.postForEntity(url, getNotificationsReq, GetClientNotificationRes.class);

        //validRes(responseEntity);
        logger.info(responseEntity.getBody().toString());
        
        return responseEntity.getBody();
    }

    @Override
    public GetNotificationsRes getNotifications(GetNotificationsReq getNotificationsReq) {
        String url = "http://10.160.200.144:9080/S2I_EnotificationModuleWeb/S2iDENServiceHTTPExport/GetNotifications";

        //logger.info(esbRestTemplate.postForObject(url, getNotificationsReq, String.class));
        
        ResponseEntity<GetNotificationsRes> responseEntity = esbRestTemplate.postForEntity(url, getNotificationsReq, GetNotificationsRes.class);

        //validRes(responseEntity);
        logger.info(responseEntity.getBody().toString());

        return responseEntity.getBody();
    }

    @Override
    public void activeClientNotification(ActiveClientNotificationReq activeClientNotificationReq) {
        String url = "http://10.160.200.144:9080/S2I_EnotificationModuleWeb/S2iDENServiceHTTPExport/ActiveClientNotification";

        //logger.info(esbRestTemplate.postForObject(url, getNotificationsReq, String.class));
        
        ResponseEntity<String> responseEntity = esbRestTemplate.postForEntity(url, activeClientNotificationReq, String.class);

        //validRes(responseEntity);
        logger.info(responseEntity.getBody().toString());
    }

    @Override
    public void deleteClientNotification(ActiveClientNotificationReq activeClientNotificationReq) {
        String url = "http://10.160.200.144:9080/S2I_EnotificationModuleWeb/S2iDENServiceHTTPExport/DeleteClientNotification";

        //logger.info(esbRestTemplate.postForObject(url, getNotificationsReq, String.class));
        
        ResponseEntity<String> responseEntity = esbRestTemplate.postForEntity(url, activeClientNotificationReq, String.class);

        //validRes(responseEntity);
        logger.info(responseEntity.getBody().toString());
    }

}
