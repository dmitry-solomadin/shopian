package me.shopian.shopian3.controller;

import me.shopian.shopian3.service.ForTestingJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ajax")
public class StatisticController {
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);


    @RequestMapping(value = "/test.json",  produces="application/json")
    @ResponseBody
    public ForTestingJson c() {
        logger.debug("Page Request: /ajax/test.json");
        return new ForTestingJson("aaa");
    }

/*

//    @RequestMapping(value = "/counts", headers = "Accept=application/json",  produces = "application/json")
//    @RequestMapping(value = "/counts", headers = "Accept=application/json; Content-Type=application/json", produces = "application/json")
//    @RequestMapping(value = "/counts",  produces="application/json")
    @RequestMapping(value = "/counts")
    @ResponseBody
    public Map<String, String> getCounts() {
        logger.debug("Page Request: /json/counts.do");
        Map<String, String> map = new HashMap<String, String>();
        map.put("client", "54321");
        map.put("request", "123412354123");
        map.put("api", "55553333");
        return map;
    }
*/

}
