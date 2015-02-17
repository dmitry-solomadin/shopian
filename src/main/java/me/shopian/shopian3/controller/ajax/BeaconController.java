package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.dao.UserDaoImpl;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.entity.Shop;
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
import java.util.*;

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


    private Beacon mapToBeacon(Map<String,String> beaconMap,Beacon beacon){
        beacon.setUuid(beaconMap.get("uuid"));
        beacon.setMajor(NumberUtils.toInt(beaconMap.get("major")));
        beacon.setMinor(NumberUtils.toInt(beaconMap.get("minor")));
        beacon.setUser(userService.findUserByUsername(beaconMap.get("user")));
        beacon.setShop(shopService.get(NumberUtils.toLong(beaconMap.get("shop"))));
        beacon.setDepartment(departmentService.get(NumberUtils.toLong(beaconMap.get("department"))));
        return beacon;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody Map<String,String> beaconMap) {
        Map map = new HashMap();
        Beacon beacon=  mapToBeacon(beaconMap, new Beacon());

        Beacon b2 = beaconService.getBayUuidMajorMinor(beacon);
        if (b2 == null) {
            beaconService.add(beacon);
        } else {
            map.put("error", "beacon with unique (UUID, major, minor) already exists: #" + b2.getId());
        }
        map.put("beacon", beacon);
        logger.info("beacon " + beacon);
        return map;
    }

    @RequestMapping(value = "list.json")
    @ResponseBody
    public Map list(HttpServletRequest request
            , @RequestParam(value = "draw", required = false, defaultValue = "0") int draw
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


    @RequestMapping(value = "info.json")
    @ResponseBody
    public Beacon list(@RequestParam(value = "id", required = true) long id) {
        return beaconService.get(id);
    }

    @RequestMapping(value = "shops.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List shops(@RequestBody User user)  {
        return shopService.list(userService.findUserByUsername(user.getUsername()), 0, 0, null, null);
    }
    @RequestMapping(value = "departments.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection shops(@RequestBody Shop shop)  {
        return departmentService.list(shop.getId());
    }
    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map del(@PathVariable long id) {
        Map map = new HashMap();
        Beacon beacon = beaconService.get(id);
        if (beacon == null || beacon.getId() < 1) {
            map.put("error", "beacon #" + id + " not found");
        } else {
            beaconService.delete(id);
        }
        return map;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map update(@RequestBody Map<String,String> beaconMap) {
        Map map = new HashMap();
        Beacon beacon=  mapToBeacon(beaconMap, beaconService.get(NumberUtils.toLong(beaconMap.get("id"))));
        if (beacon != null) {
            beaconService.update(beacon);
        } else {
            map.put("error", "Магазина с id:" + beacon.getId() + " не существует");
        }
        return map;
    }
}
