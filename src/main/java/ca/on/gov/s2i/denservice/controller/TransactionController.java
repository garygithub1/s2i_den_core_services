package ca.on.gov.s2i.denservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.common.jwt.JwtComp;
import ca.on.gov.common.model.session.Session;
import ca.on.gov.common.model.session.UserProfile;
import ca.on.gov.common.service.SessionService;

@RestController
@RequestMapping("/v1/tx")
public class TransactionController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired(required = false)
    private JwtComp jwtComp;

    @Autowired(required=false)
    private SessionService sessionService;

    // @Autowired(required=false)

    @SuppressWarnings("rawtypes")
    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        logger.debug("cross domain 1");
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = produces)
    //"Internet" "Simulator" ESDI
    public String[] addTransaction(HttpServletRequest request, HttpServletResponse response, 
            @RequestHeader(value = "ChannelId", defaultValue="Internet") String channelId,
            @RequestHeader(value = "Language", defaultValue="EN") String language, 
            @RequestHeader(value = "Device", defaultValue="Desktop") String device, @RequestParam() String serviceType) {
        //String transactionId = "201661480024";

        // get transactionId by call ws
        Session session = new Session();
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName( "First");
        userProfile.setLastName( "Last");
        userProfile.setCcSecondary( false);
        userProfile.setOnekeyPID( "fakedtestid123");
        session.setDeviceClass(device);
        
        //TODO think about default value
        //session.setIpAddress( "127.0.0.1");
        session.setIpAddress( request.getHeader("X-FORWARDED-FOR") );
        session.setChannelID( channelId );

        session.setLanguage(language);
        session.setProductBundle(serviceType);// "DVS"
        session.setTransactionKey(serviceType);
        session.setUserAgent( "TestCase");
        session.setUserProfile(userProfile);
        
        sessionService.createTransaction(session);
        //logger.info(abc.toString());

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("txId", session.getTransactionId());
        String jwt = jwtComp.encode(content, null);

        return new String[] { jwt };
    }
}
