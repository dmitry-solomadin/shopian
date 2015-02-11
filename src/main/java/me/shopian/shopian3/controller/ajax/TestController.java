package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.controller.WelcomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/ajax")
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    private static final Random random = new Random();

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
    @RequestMapping(value = "/test.txt",  produces = "text/plain")
    @ResponseBody
    public String c1() {
        List<String> list = new ArrayList();
        logger.debug("Page Request: /json/test.txt");
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(list);
        return "saaa";
    }
    @RequestMapping(value = "/test.json",  produces = "application/json")
    @ResponseBody
    public  Map<String, String> c2() {
        logger.debug("Page Request: /json/test.json");
        Map<String, String> map = new HashMap();
        map.put("a", "a");
        map.put("b", "b");
        return map;
    }
}
