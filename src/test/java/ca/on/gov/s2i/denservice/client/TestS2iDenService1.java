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
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsReq;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.test.properties")
public class TestS2iDenService1 {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    private static final Logger logger = LoggerFactory.getLogger(TestS2iDenService1.class);  
    
    @Autowired
    S2iDenService s2iDenService;

    @Test
    public void testList() {
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
        GetNotificationsReq getNotificationsReq = null;
        
        getNotificationsReq = new GetNotificationsReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setEmail("jason.li@ontario.ca");

        s2iDenService.getNotifications(getNotificationsReq);
        
    }
}
