package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.dao.UserDaoImpl;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.BeaconService;
import me.shopian.shopian3.service.DepartmentServiceImpl;
import me.shopian.shopian3.service.ShopService;
import me.shopian.shopian3.service.UserServiceImpl;
import me.shopian.shopian3.util.DataTableUtils;
import me.shopian.shopian3.util.IdTitle;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax/beacon")
public class BeaconController {
    private static Logger logger = LoggerFactory.getLogger(BeaconController.class);

    @Qualifier("beaconServiceImpl")
    @Autowired(required = true)
    private BeaconService beaconService;

    @Qualifier("userDetailsService")
    @Autowired
    private UserServiceImpl userService;

    @Qualifier("shopServiceImpl")
    @Autowired
    private ShopService shopService;

    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentServiceImpl departmentService;


    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody Map<String,String> beaconMap) {
        System.out.println("beaconMap = [" + beaconMap + "]");
        Beacon beacon= new Beacon();
        beacon.setUuid(beaconMap.get("uuid"));
        beacon.setMajor(NumberUtils.toInt(beaconMap.get("major")));
        beacon.setMinor(NumberUtils.toInt(beaconMap.get("minor")));
        logger.info("Beacon controller: " + beacon);
        Map map = new HashMap();
        Beacon b2 = beaconService.getBayUuidMajorMinor(beacon);
        logger.info("b2: " + b2);
        if (b2 == null) {
            beacon.setUser(userService.findUserByUsername(beaconMap.get("user")));
            beacon.setShop(shopService.get(NumberUtils.toLong(beaconMap.get("shop"))));
            beacon.setDepartment(departmentService.get(NumberUtils.toLong(beaconMap.get("department"))));
            beaconService.add(beacon);
        } else {
            map.put("error", "beacon with unique (uid, major, minor) already exists: #" + b2.getId());
        }
        map.put("beacon", beacon);
        logger.info("beacon " + beacon);
        return map;
    }

    @RequestMapping(value = "list.json")
    @ResponseBody
    public Map list(HttpServletRequest request,
                    @RequestParam(value = "draw", required = false, defaultValue = "0") int draw
            , @RequestParam(value = "start", required = false, defaultValue = "0") int start
            , @RequestParam(value = "length", required = false, defaultValue = "10") int length
            , @RequestParam(value = "search[value]", required = false, defaultValue = "") String searchText
    ) {

        Map map = new HashMap();
        map.put("draw", draw);
        map.put("data", beaconService.list(start, length, DataTableUtils.getColumnDirectionList(request), searchText));
        map.put("recordsTotal", beaconService.count());
        map.put("recordsFiltered", beaconService.count());
        return map;
    }


/*
    @RequestMapping(value = "users.json")
    @ResponseBody
    public List list() {
        List<String> list = new ArrayList<>();
        for (User user: userService.list("ROLE_USER") ){
            list.add(user.getUsername());
        }
        return list;
    }*/

    @RequestMapping(value = "shops.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List shops(@RequestBody User user)  {
        return shopService.list(userService.findUserByUsername(user.getUsername()), 0, 0, null, null);
    }
}
