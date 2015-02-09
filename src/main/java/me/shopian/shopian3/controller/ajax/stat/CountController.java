package me.shopian.shopian3.controller.ajax.stat;

import me.shopian.shopian3.controller.WelcomeController;
import me.shopian.shopian3.service.ForTestingJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/ajax/stat/count")
public class CountController {
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private static final Random random = new Random();

    @RequestMapping(value = "/client.json",  produces = "application/json")
    @ResponseBody
    public  Map<String, String> client() {
        Map<String, String> map = new HashMap();
        map.put("count", ""+ Math.abs(random.nextInt(100_000)) );
        return map;
    }
    @RequestMapping(value = "/beacon.json",  produces = "application/json")
    @ResponseBody
    public  Map<String, String> beacon() {
        Map<String, String> map = new HashMap();
        map.put("count", ""+ Math.abs(random.nextInt(1000)) );
        return map;
    }
    @RequestMapping(value = "/api.json",  produces = "application/json")
    @ResponseBody
    public  Map<String, String> api() {
        Map<String, String> map = new HashMap();
        map.put("count", ""+ Math.abs(random.nextInt()) );
        return map;
    }
    @RequestMapping(value = "/shop.json",  produces = "application/json")
    @ResponseBody
    public  Map<String, String> shop() {
        Map<String, String> map = new HashMap();
        map.put("count", ""+ 1 + Math.abs(random.nextInt(10)) );
        return map;
    }

}
