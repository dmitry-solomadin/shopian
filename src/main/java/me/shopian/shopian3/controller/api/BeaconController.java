package me.shopian.shopian3.controller.api;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.shopian.shopian3.entity.*;
import me.shopian.shopian3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/beacon")
public class BeaconController {


    @Qualifier("beaconServiceImpl")
    @Autowired
    private BeaconServiceImpl beaconService;

    @Qualifier("userMobileServiceImpl")
    @Autowired
    private UserMobileServiceImpl userMobileService;

    @Qualifier("logServiceImpl")
    @Autowired
    private LogServiceImpl logService;

    @Qualifier("eventServiceImpl")
    @Autowired
    private EventServiceImpl eventService;

    @Qualifier("adServiceImpl")
    @Autowired
    private AdServiceImpl adService;


    /**
     * @param token
     * @param eventName
     * @param eventDate  @DateTimeFormat requared joda-time
     * @param uuid
     * @param major
     * @param minor
     * @return
     *
     * curl -X POST --data "token=064e9471-9730-4f76-ae4e-207a070a4b09&eventName=qqq&eventDate=2012-01-02 12:13:14&uuid=111&major=22&minor=444"  'http://localhost:8080/api/beacon/inZone' ;echo
     *
     */

    @JsonIgnoreProperties({"id"})
    @JsonFilter("id")

    @RequestMapping(value = "inZone",method = {RequestMethod.POST})
    @ResponseBody
    public Map list(
            @RequestParam(value = "token", required = true) String token
            ,@RequestParam(value = "eventName", required = true) String eventName
            ,@RequestParam(value = "eventDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")   Date eventDate
            ,@RequestParam(value = "uuid", required = true) String uuid
            ,@RequestParam(value = "major", required = true) int major
            ,@RequestParam(value = "minor", required = true) int minor
    ) {
        System.out.println("token = [" + token + "], eventName = [" + eventName + "], eventDate = [" + eventDate + "], uuid = [" + uuid + "], major = [" + major + "], minor = [" + minor + "]");

        Map map = new HashMap();
        UserMobile user = userMobileService.get(token);
        if (user==null){
            map.put("error","token '"+token+"' not found");
            return map;
        }
        Beacon beacon = beaconService.getBayUuidMajorMinor(uuid,major,minor);
        if (beacon==null){
            map.put("error","beacon with uuid='"+uuid+"' major='"+major+"' minor='"+minor+"' not found");
            return map;
        }

        Event event = eventService.get(eventName);
        if (event==null){
            map.put("error","event '"+eventName+"' not found");
            return map;
        }

        Log log = new Log();
        log.setBeacon(beacon);
        log.setDateMobile(eventDate);
        log.setEvent(event);
        log.setUserMobile(user);
        List<Ad> ads = adService.getListForBeacon(beacon);
        log.setAds(ads);

        logService.add(log);

//        map.put("ok",true);
        map.put("campaigns",ads);
//        map.put("log",log);


/*        User user=userService.findUserByToken(productToken);
        if (user==null){
            map.put("error","productToken '"+productToken+"' not found");
            return map;
        }
        UserMobile userMobile=new UserMobile(user);
        userMobileImpl.add(userMobile);
        map.put("token",userMobile.getToken());*/
        return map;
    }
}
