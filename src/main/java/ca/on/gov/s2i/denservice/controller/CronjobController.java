package ca.on.gov.s2i.denservice.controller;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ca.on.gov.s2i.denservice.model.cronjob.SingleTask;

import ca.on.gov.s2i.denservice.model.cronjob.Cronjob;
import ca.on.gov.s2i.denservice.model.cronjobs.Cronjobs;

@RestController
@RequestMapping("/v1/cronjob")
@Validated
public class CronjobController extends BaseController {
    protected static Logger logger = LoggerFactory.getLogger(CronjobController.class);

    //@CrossOrigin(origins = crossDomain)
    //@RequestMapping(value = "/", method = RequestMethod.GET, produces = produces)
    // @Pattern(regexp = RegexComp.POSTAL_CODE)
    public void checkDlSubscription(@RequestParam(value="date", defaultValue="date") String date, @RequestParam(value="owner", defaultValue="owner") String owner) throws ParseException {
        /*
        CronExpression cronTrigger = new CronExpression("1 1 * ? * *");
        Date ss = new Date();
        Date tt = cronTrigger.getNextValidTimeAfter(ss);*/

        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://10.160.200.104:8001/apis/batch/v1beta1/namespaces/kube-system/cronjobs";
        System.out.println(uri);

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", "Basic " + base64Creds);
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //headers.add("Authorization", "Basic " + base64Creds);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));  
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //HttpEntity<String> map = new HttpEntity<String>("", headers);//gson.toJson(webHookRequest)
        ResponseEntity<Cronjobs> response = restTemplate.getForEntity(uri, Cronjobs.class);
        System.out.println(response.getStatusCode()+"");
        System.out.println(response.getBody());
        System.out.println(response.getHeaders().toString());
        
        Cronjobs cronjobs = response.getBody();
        
        List<Cronjob> itemes = cronjobs.getItems();
        
        if ( !"date".equals(date) ) {
            for(int i=0;i<itemes.size();i++) {
                boolean skip = false;
                /*
                if (i>0) {
                    skip = true;
                }*/
                String str = itemes.get(i).getSpec().getSchedule();
                String[] strArray = str.split(" ");
                if ( strArray.length==5 ) {
                    strArray[2] = "?";
                    str = String.join(" ", strArray);
                    str = "0 " + str;
                } else {
                    strArray[3] = "?";
                    str = String.join(" ", strArray);
                }
                System.out.println("cron expression:"+str);
                if (str!=null) {
                    //continue;
                }
                CronExpression cronExpression = new CronExpression(str);
                
                String string = date;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateFrom = format.parse(string);
                dateFrom = new Date(dateFrom.getTime()-1);
                Date dateWillRun = cronExpression.getNextValidTimeAfter(dateFrom);
                System.out.println("dateFrom:"+dateFrom);
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateFrom);
                cal.add(Calendar.DAY_OF_YEAR, 1);
                Date dateEnd= cal.getTime();
                System.out.println("dateWillRun:"+dateWillRun);
                System.out.println("dateEnd:"+dateEnd);
                if ( dateWillRun.compareTo(dateEnd)>0 ) {
                    skip = true;
                }
                
                if (skip) {
                    itemes.remove(i);
                    i--;
                }
            }
        }
        

        if ( !"owner".equals(owner) ) {
            for(int i=0;i<itemes.size();i++) {
                boolean skip = false;
                /*
                if (i>0) {
                    skip = true;
                }*/
                String str = itemes.get(i).getMetadata().getName();
                System.out.println("job name:"+str);
                if ( !str.startsWith(owner) ) {
                    skip = true;
                }
                
                if (skip) {
                    itemes.remove(i);
                    i--;
                }
                
            }
        }

    }


    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    //2018-09-01&endDate=2018-09-02&owner=job
    //String[]
    public List<SingleTask> cronjobList(@RequestParam(value="startDate", defaultValue="2018-09-01") String startDate, @RequestParam(value="endDate") String endDate, @RequestParam(value="owner", defaultValue="job") String owner) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://10.160.200.104:8001/apis/batch/v1beta1/namespaces/kube-system/cronjobs";
        System.out.println(uri);

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", "Basic " + base64Creds);
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //headers.add("Authorization", "Basic " + base64Creds);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));  
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //HttpEntity<String> map = new HttpEntity<String>("", headers);//gson.toJson(webHookRequest)
        ResponseEntity<Cronjobs> response = restTemplate.getForEntity(uri, Cronjobs.class);
        System.out.println(response.getStatusCode()+"");
        System.out.println(response.getBody());
        System.out.println(response.getHeaders().toString());
        
        Cronjobs cronjobs = response.getBody();
        
        List<Cronjob> itemes = cronjobs.getItems();
        
        List<SingleTask> result = new ArrayList<SingleTask>();
        
        for (Cronjob cronjob: itemes) {
            if ( !"owner".equals(owner) ) {
                String str = cronjob.getMetadata().getName();

                if ( !str.startsWith(owner) ) {
                    continue;
                }
            }

            String str = cronjob.getSpec().getSchedule();
            String[] strArray = str.split(" ");
            if ( strArray.length==5 ) {
                strArray[2] = "?";
                str = String.join(" ", strArray);
                str = "0 " + str;
            } else {
                strArray[3] = "?";
                str = String.join(" ", strArray);
            }
            System.out.println("cron expression:"+str);
            if (str!=null) {
                //continue;
            }
            CronExpression cronExpression = new CronExpression(str);
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = format.parse(startDate);
            //dateFrom = new Date(dateFrom.getTime()-1);
            System.out.println("dateFrom:"+dateFrom);

            Date dateEnd = format.parse(endDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateEnd);
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateEnd = cal.getTime();
            System.out.println("dateEnd:"+dateEnd);
            
            Date dateWillRun = null;//cronExpression.getNextValidTimeAfter(dateFrom);
            SingleTask singleTask = null;
            for(int i=0;i<100;i++) {
                dateWillRun = cronExpression.getNextValidTimeAfter(dateFrom);
                if ( dateWillRun.compareTo(dateEnd)<0 ) {
                    singleTask = new SingleTask();
                    singleTask.setCronjobId(cronjob.getMetadata().getName());
                    singleTask.setRunningDate(dateWillRun);
                    result.add(singleTask);
                    System.out.println( singleTask );
                    
                    dateFrom = dateWillRun;
                } else {
                    break;
                }
            }
        }
        
        return result;
        //return new String[] {ss + ":hello " + name + "!!" + tt};
    }

    
}
