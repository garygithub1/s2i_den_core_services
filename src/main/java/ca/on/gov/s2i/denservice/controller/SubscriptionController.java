package ca.on.gov.s2i.denservice.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.common.exception.ExceptionComp;
import ca.on.gov.common.jwt.RequestHeaderComp;
import ca.on.gov.common.jwt.model.RequestHeader;
import ca.on.gov.s2i.denservice.client.S2iDenService;
import ca.on.gov.s2i.denservice.client.model.Email;
import ca.on.gov.s2i.denservice.client.model.EsbMessage;
import ca.on.gov.s2i.denservice.client.model.ProfileInfo;
import ca.on.gov.s2i.denservice.client.model.ResponseHeader;
import ca.on.gov.s2i.denservice.client.model.s2iden.ActiveClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetClientNotificationRes;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsReq;
import ca.on.gov.s2i.denservice.client.model.s2iden.GetNotificationsRes;

@RestController
@RequestMapping("/v1/subscription")
@Validated
public class SubscriptionController extends BaseController {
    protected static Logger logger = LoggerFactory.getLogger(SubscriptionController.class);    

    @Autowired
    private S2iDenService s2iDenService;

    // @Autowired(required=false)
    @Autowired
    private RequestHeaderComp requestHeaderComp;
    
    @Autowired
    private ExceptionComp exceptionComp;

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/dl/{dl}", method = RequestMethod.GET, produces = produces)
    // @Pattern(regexp = RegexComp.POSTAL_CODE)
    public GetClientNotificationRes checkDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String dl, @Valid @RequestParam() String postCode) {
        /**/
        if ( postCode==null ) {
            exceptionComp.throwException("code-101", "fadsfdas", false);
        }
        
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("DLRA");
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());// L22222222222222
        getNotificationsReq.setPostCode(postCode.toUpperCase());// "P1P1P1"

        GetClientNotificationRes getNotificationsRes = s2iDenService.getClientNotification(getNotificationsReq);
        
        this.checkResponse(getNotificationsRes.getResponseHeader());

        return getNotificationsRes;
    }

    private void checkResponse(ResponseHeader responseHeader) {
        if ( responseHeader!=null && "false".equals(responseHeader.getResponseCode()) ) {
            Map<String, String> errorMap = new TreeMap<String, String>();
            for (EsbMessage esbMessage: responseHeader.getResponseMessage()) {
                errorMap.put(esbMessage.getMessageCode(), esbMessage.getLogMessage());
            }
            exceptionComp.throwException(errorMap, false);
        }
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/dl/{dl}", method = RequestMethod.GET, produces = produces)
    // exception
    public GetClientNotificationRes checkVlDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String plateNum, @PathVariable() String dl, @RequestParam() String postCode, @RequestParam(defaultValue="N") String withDlra) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        String noticiationType = "VLRA";
        if ( "Y".equals(withDlra) ) {
            noticiationType = "DLRA_VLRA";
        }
        getNotificationsReq.setNotificationType(noticiationType);
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());// L22222222222222
        getNotificationsReq.setPostCode(postCode.toUpperCase());// "P1P1P1"

        GetClientNotificationRes getNotificationsRes = s2iDenService.getClientNotification(getNotificationsReq);

        return getNotificationsRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/rin/{rin}", method = RequestMethod.GET, produces = produces)
    // exception
    public GetClientNotificationRes checkVlRinSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String plateNum, @PathVariable() String rin) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setRin(rin);

        GetClientNotificationRes getNotificationsRes = s2iDenService.getClientNotification(getNotificationsReq);

        return getNotificationsRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/dl/{dl}/client", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Language", defaultValue="en") String language,
            @PathVariable() String dl, @RequestParam() String postCode, @RequestParam() String email) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("DLRA");
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());
        getNotificationsReq.setPostCode(postCode.toUpperCase());
        
        ProfileInfo profileInfo = new ProfileInfo();
        //profileInfo.setFirstName(firstName);
        //profileInfo.setLastName(lastName);
        profileInfo.setLangPref(language);
        Email emailObject = new Email();
        emailObject.setEmail(email);
        profileInfo.setEmail(emailObject);
        getNotificationsReq.setProfileInfo(profileInfo);

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/dl/{dl}/pid", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String dl, @RequestParam() String postCode, @RequestParam() String pid) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("DLRA");
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());
        getNotificationsReq.setPostCode(postCode.toUpperCase());
        
        getNotificationsReq.setPid(pid);;

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/dl/{dl}/client", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addVlDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Language", defaultValue="en") String language,
            @PathVariable() String plateNum, @PathVariable() String dl, @RequestParam() String postCode, @RequestParam() String email, @RequestParam(defaultValue="N") String withDlra) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        String noticiationType = "VLRA";
        if ( "Y".equals(withDlra) ) {
            noticiationType = "DLRA_VLRA";
        }
        getNotificationsReq.setNotificationType(noticiationType);
        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());
        getNotificationsReq.setPostCode(postCode.toUpperCase());
        
        ProfileInfo profileInfo = new ProfileInfo();
        //profileInfo.setFirstName(firstName);
        //profileInfo.setLastName(lastName);
        profileInfo.setLangPref(language);
        Email emailObject = new Email();
        emailObject.setEmail(email);
        profileInfo.setEmail(emailObject);
        getNotificationsReq.setProfileInfo(profileInfo);

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/dl/{dl}/pid", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addVlDlSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String plateNum, @PathVariable() String dl, @RequestParam() String postCode, @RequestParam() String pid, @RequestParam(defaultValue="N") String withDlra) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        String noticiationType = "VLRA";
        if ( "Y".equals(withDlra) ) {
            noticiationType = "DLRA_VLRA";
        }
        getNotificationsReq.setNotificationType(noticiationType);
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setDriverLicenceNumber(dl.toUpperCase());
        getNotificationsReq.setPostCode(postCode.toUpperCase());
        
        getNotificationsReq.setPid(pid);

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/rin/{rin}/client", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addVlRinSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Language", defaultValue="en") String language,
            @PathVariable() String plateNum, @PathVariable() String rin, @RequestParam() String email) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setRin(rin);
        
        ProfileInfo profileInfo = new ProfileInfo();
        //profileInfo.setFirstName(firstName);
        //profileInfo.setLastName(lastName);
        profileInfo.setLangPref(language);
        Email emailObject = new Email();
        emailObject.setEmail(email);
        profileInfo.setEmail(emailObject);
        getNotificationsReq.setProfileInfo(profileInfo);

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/plateNum/{plateNum}/rin/{rin}/pid", method = RequestMethod.POST, produces = produces)
    public GetClientNotificationRes addVlRinSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String plateNum, @PathVariable() String rin, @RequestParam() String pid) {
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetClientNotificationReq getNotificationsReq = new GetClientNotificationReq();
        getNotificationsReq.setRequestHeader(requestHeader);

        getNotificationsReq.setNotificationType("VLRA");
        getNotificationsReq.setPlateNumber(plateNum);
        getNotificationsReq.setRin(rin);
        
        getNotificationsReq.setPid(pid);

        GetClientNotificationRes getClientNotificationRes = s2iDenService.addClientNotification(getNotificationsReq);

        return getClientNotificationRes;
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/pid/{pid}/activation", method = RequestMethod.POST, produces = produces)
    public void activeSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String pid) {
        //TODO parse pid from input or from cache
        //String pid = pidInput;
        logger.info("pid:" + pid);

        RequestHeader requestHeader = requestHeaderComp.parse(request);
        ActiveClientNotificationReq activeClientNotificationReq = new ActiveClientNotificationReq();
        activeClientNotificationReq.setRequestHeader(requestHeader);
        activeClientNotificationReq.setPid(pid);
        
        s2iDenService.activeClientNotification(activeClientNotificationReq);
    }

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/pid/{pid}/delete", method = RequestMethod.POST, produces = produces)
    public void deleteSubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String pid) {
        //String pid = pidInput;
        logger.info("pid:" + pid);

        RequestHeader requestHeader = requestHeaderComp.parse(request);
        ActiveClientNotificationReq activeClientNotificationReq = new ActiveClientNotificationReq();
        activeClientNotificationReq.setRequestHeader(requestHeader);
        activeClientNotificationReq.setPid(pid);
        
        s2iDenService.deleteClientNotification(activeClientNotificationReq);
    }
    
    //TODO support delete from url

    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = produces)
    public GetNotificationsRes querySubscription(HttpServletRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization") String authorization,
            @org.springframework.web.bind.annotation.RequestHeader(value = "ChannelId") String channelId,
            @PathVariable() String email) {
        logger.info("email:" + email);
        
        RequestHeader requestHeader = requestHeaderComp.parse(request);
        GetNotificationsReq getNotificationsReq = new GetNotificationsReq();
        getNotificationsReq.setRequestHeader(requestHeader);
        
        getNotificationsReq.setEmail(email);
        
        GetNotificationsRes getNotificationsRes = s2iDenService.getNotifications(getNotificationsReq);
        
        return getNotificationsRes;
    }
}
