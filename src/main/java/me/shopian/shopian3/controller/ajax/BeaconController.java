package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.service.BeaconService;
import me.shopian.shopian3.util.DataTableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ajax/beacon")
public class BeaconController {
    private static Logger logger = LoggerFactory.getLogger(BeaconController.class);

    @Qualifier("beaconServiceImpl")
    @Autowired(required = true)
    private BeaconService beaconService;

    /*    @RequestMapping(value= "add", method = RequestMethod.POST)
        public String add(@ModelAttribute("beacon") Beacon beacon){

                this.beaconService.add(beacon);
            return "ok";

        }*/
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody Beacon beacon) {
        logger.info("Beacon controller: " + beacon);
        Map map = new HashMap();
        Beacon b2 = beaconService.getBayUuidMajorMinor(beacon);
        logger.info("b2: " + b2);
        if (b2 == null) {
            beaconService.add(beacon);
            map.put("beacon", beacon);
        } else {
            map.put("beacon", beacon);
            map.put("error", "beacon with unique (uid, major, minor) already exists:" + b2.toString());
        }
        logger.info("beacon " + beacon);
        return map;
    }

/*
draw:1
start:0
length:10
search[value]:
search[regex]:false
 */


    @RequestMapping(value = "list.json")
    @ResponseBody
    public Map list(HttpServletRequest request,
                    @RequestParam(value = "draw", required = false, defaultValue = "0") int draw
            , @RequestParam(value = "start", required = false, defaultValue = "0") int start
            , @RequestParam(value = "length", required = false, defaultValue = "10") int length
            , @RequestParam(value = "search[value]", required = false, defaultValue = "") String searchText
    ) {

        System.out.println("getColumnDirectionList = " + DataTableUtils.getColumnDirectionList(request));
        System.out.println("getColumnList = " + DataTableUtils.getColumnList(request));
        System.out.println("getColumnMap = " + DataTableUtils.getColumnMap(request));

        Map map = new HashMap();
        map.put("draw", draw);
        map.put("data", beaconService.list(start, length, DataTableUtils.getColumnDirectionList(request), searchText));
        map.put("recordsTotal", beaconService.count());
        map.put("recordsFiltered", beaconService.count());
        return map;
    }
}
