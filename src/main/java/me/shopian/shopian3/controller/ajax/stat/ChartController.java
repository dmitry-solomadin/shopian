package me.shopian.shopian3.controller.ajax.stat;

import me.shopian.shopian3.controller.WelcomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/ajax/stat/chart")
public class ChartController {
    private static Logger logger = LoggerFactory.getLogger(ChartController.class);
    private static final Random random = new Random();

    @RequestMapping(value = "/clients.json", produces = "application/json")
    @ResponseBody
    public List clients() {
        List data = new ArrayList();
        for (int i = 1; i < 31; i++) {
            List node =  new ArrayList();
            node.add( i );
            node.add((long) 1000 + random.nextInt(i * 50));
            data.add(node);
        }
        Map map = new HashMap();
        map.put("data", data);
        List list = new ArrayList();
        list.add(map);
        return list;
    }

    @RequestMapping(value = "/time.json", produces = "application/json")
    @ResponseBody
    public List time() {
        List data = new ArrayList();
        for (int i = 1; i < 31; i++) {
            List node =  new ArrayList();
            node.add( i );
            node.add((long) 1000 + random.nextInt(i * 50));
            data.add(node);
        }
        Map map = new HashMap();
        map.put("data", data);
        List list = new ArrayList();
        list.add(map);
        return list;
    }




    private List<Map> getActionList() {
        List list = new ArrayList();
        for (int i = 1; i <= 10; i++) {
            List node = new ArrayList();
            node.add(i);
            node.add( Math.round(Math.random() * 30));
            list.add(node);
        }
        return list;
    }

    public static final Map<String, Object> LINES = new HashMap();

    static {
        LINES.put("lines", 1);
    }

    @RequestMapping(value = "/action.json", produces = "application/json")
    @ResponseBody
    public List action() {

        List list = new ArrayList();
        Map map = new HashMap();
        map.put("label", "получено");
        map.put("data", getActionList());
        map.put("lines", LINES);
        map.put("shadowSize", 0);
        list.add(map);

        map = new HashMap();
        map.put("label", "просмотрено");
        map.put("data", getActionList());
        map.put("lines", LINES);
        map.put("shadowSize", 0);
        list.add(map);

        map = new HashMap();
        map.put("label", "игнорировано");
        map.put("data", getActionList());
        map.put("lines", LINES);
        map.put("shadowSize", 0);
        list.add(map);

        return list;
    }
}
