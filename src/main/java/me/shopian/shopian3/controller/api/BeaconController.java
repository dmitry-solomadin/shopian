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

import java.util.*;

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
     * @param eventDate @DateTimeFormat requared joda-time
     * @param uuid
     * @param major
     * @param minor
     * @return JSON
     *
     * test
     *   curl -X POST --data "token=7bf581bb-4508-48f3-a638-66f619f56738&eventName=BEACON_ZONE_1&eventDate=2012-01-02 12:13:14&uuid=f7826da6-4fa2-4e98-8024-bc5b71e0893e&major=49494&minor=11946"  http://admin.shopian.me/api/beacon/inZone
     *   curl -X POST --data "token=7bf581bb-4508-48f3-a638-66f619f56738&eventName=BEACON_ZONE_1&eventDate=2012-01-02 12:13:14"  http://admin.shopian.me/api/beacon/inZone
     *
     */

    @JsonIgnoreProperties({"id"})
    @JsonFilter("id")

    @RequestMapping(value = "inZone", method = {RequestMethod.POST})
    @ResponseBody
    public Map list(
            @RequestParam(value = "token", required = true) String token
            , @RequestParam(value = "eventName", required = true) String eventName
            , @RequestParam(value = "eventDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date eventDate
            , @RequestParam(value = "uuid", required = false, defaultValue = "") String uuid
            , @RequestParam(value = "major", required = false, defaultValue = "0") int major
            , @RequestParam(value = "minor", required = false, defaultValue = "0") int minor
    ) {
        System.out.println("token = [" + token + "], eventName = [" + eventName + "], eventDate = [" + eventDate + "], uuid = [" + uuid + "], major = [" + major + "], minor = [" + minor + "]");

        Map map = new HashMap();
        UserMobile user = userMobileService.get(token);
        if (user == null) {
            map.put("error", "token '" + token + "' not found");
            return map;
        }
        Log log = new Log();
        log.setUserMobile(user);
        if (!"".equals(uuid)) {
            Beacon beacon = beaconService.getBayUuidMajorMinor(uuid, major, minor);
            if (beacon == null) {
                map.put("error", "beacon with uuid='" + uuid + "' major='" + major + "' minor='" + minor + "' not found");
                return map;
            }
            log.setBeacon(beacon);
            List<Ad> ads = adService.getListForBeacon(beacon);
            log.setAds(ads);
            map.put("campaigns", ads);
        }else{
            map.put("campaigns", new ArrayList<Ad>());
        }

        Event event = eventService.get(eventName);
        if (event == null) {
            map.put("error", "event '" + eventName + "' not found");
            return map;
        }

        log.setDateMobile(eventDate);
        log.setEvent(event);

        logService.add(log);

        return map;
    }
}
