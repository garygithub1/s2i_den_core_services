package ca.on.gov.s2i.denservice.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ca.on.gov.common.jwt.model.RequestHeader;
import ca.on.gov.s2i.denservice.client.S2iDenService;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationReq;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.test.properties")
public class TestS2iDenService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    private static final Logger logger = LoggerFactory.getLogger(TestS2iDenService.class);  
    
    @Autowired
    S2iDenService s2iDenService;

    @Test
    public void testDlraSequence() {
        logger.info("testSequence:" + (s2iDenService==null));

        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setTransactionId("201661480024");

        requestHeader.setInternetIPAddress( "1.1.1.1" );
        requestHeader.setChannelId( "Simulator" );
        requestHeader.setTimestamp(sdf.format(new Date()));//"2015-07-13T15:44:40"

        requestHeader.setLanguage("EN");
        requestHeader.setTerminalId("X");
        //TODO confirm MCBS
        requestHeader.setServiceProviderCode("MCBS");
        GetClientNotificationReq getNotificationsReq = null;
        
        getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("DLRA");
        getNotificationsReq.setDriverLicenceNumber("L22222222222222");//
        getNotificationsReq.setPostCode("P1P1P1");//

        s2iDenService.getClientNotification(getNotificationsReq);
        
    }


    @Test
    public void testVlraSequence() {
        logger.info("testSequence:" + (s2iDenService==null));

        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setTransactionId("201661480024");

        requestHeader.setInternetIPAddress( "1.1.1.1" );
        requestHeader.setChannelId( "Simulator" );
        requestHeader.setTimestamp(sdf.format(new Date()));//"2015-07-13T15:44:40"

        requestHeader.setLanguage("EN");
        requestHeader.setTerminalId("X");
        //TODO confirm MCBS
        requestHeader.setServiceProviderCode("MCBS");
        GetClientNotificationReq getNotificationsReq = null;
        
        getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber("11111111");
        getNotificationsReq.setDriverLicenceNumber("L22222222222222");//
        getNotificationsReq.setPostCode("P1P1P1");//

        s2iDenService.getClientNotification(getNotificationsReq);
    }


    @Test
    public void testVlraRinSequence() {
        logger.info("testSequence:" + (s2iDenService==null));

        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setTransactionId("201661480024");

        requestHeader.setInternetIPAddress( "1.1.1.1" );
        requestHeader.setChannelId( "Simulator" );
        requestHeader.setTimestamp(sdf.format(new Date()));//"2015-07-13T15:44:40"

        requestHeader.setLanguage("EN");
        requestHeader.setTerminalId("X");
        //TODO confirm MCBS
        requestHeader.setServiceProviderCode("MCBS");
        GetClientNotificationReq getNotificationsReq = null;
        
        getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber("11111111");
        getNotificationsReq.setRin("222222222");

        s2iDenService.getClientNotification(getNotificationsReq);
    }
}
