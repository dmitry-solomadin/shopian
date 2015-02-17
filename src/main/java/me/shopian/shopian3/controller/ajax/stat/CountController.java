package me.shopian.shopian3.controller.ajax.stat;

import me.shopian.shopian3.service.BeaconServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Controller
@RequestMapping("/ajax/stat/count")
public class CountController {
    private static Logger logger = LoggerFactory.getLogger(CountController.class);
    private static final Random random = new Random();
    @Qualifier("beaconServiceImpl")
    @Autowired
    private BeaconServiceImpl beaconService;

    @RequestMapping(value = "/client.json", produces = "application/json")
    @ResponseBody
    public Map<String, String> client() {
        Map<String, String> map = new HashMap();
        map.put("count", "" + Math.abs(random.nextInt(100_000)));
        return map;
    }

    @RequestMapping(value = "/beacon.json", produces = "application/json")
    @ResponseBody
    public Map<String, String> beacon() {

        Map<String, String> map = new HashMap();
        map.put("count", "" + Math.abs(random.nextInt(1000)));
        map.put("count", "" + beaconService.count());
        return map;
    }

    @RequestMapping(value = "/api.json", produces = "application/json")
    @ResponseBody
    public Map<String, String> api() {
        Map<String, String> map = new HashMap();
        map.put("count", "" + Math.abs(random.nextInt()));
        return map;
    }

    @RequestMapping(value = "/shop.json", produces = "application/json")
    @ResponseBody
    public Map<String, String> shop() {
        Map<String, String> map = new HashMap();
        map.put("count", "" + 1 + Math.abs(random.nextInt(10)));
        return map;
    }

}
