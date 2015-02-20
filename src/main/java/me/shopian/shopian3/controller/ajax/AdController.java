package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.DepartmentServiceImpl;
import me.shopian.shopian3.service.ShopService;
import me.shopian.shopian3.service.UserService;
import me.shopian.shopian3.service.AdServiceImpl;
import me.shopian.shopian3.util.DataTableUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ajax/ad")
public class AdController {
    private static Logger logger = LoggerFactory.getLogger(AdController.class);

    @Qualifier("shopServiceImpl")
    @Autowired(required = true)
    private ShopService shopService;

    @Qualifier("userDetailsService")
    @Autowired(required = true)
    private UserService userService;

    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentServiceImpl departmentService;

    @Qualifier("adServiceImpl")
    @Autowired
    private AdServiceImpl adService;

    //help: http://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
    private final DateFormat  format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    private Ad mapToBeacon(Map<String,String> map, Ad ad) {
        if (ad==null) ad= new Ad();
        ad.setTitle(map.get("title"));
        ad.setPushText(map.get("pushText"));
        ad.setContent(map.get("content"));
        ad.setUser(userService.findUserByUsername(map.get("user")));
        ad.setShop(shopService.get(NumberUtils.toLong(map.get("shop"))));
        ad.setDepartment(departmentService.get(NumberUtils.toLong(map.get("department"))));

        try {
            ad.setDateStart(format.parse(map.get("dateStart")));
            ad.setDateStop(format.parse(map.get("dateStop")));
        } catch (ParseException e) {
        }
        return ad;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody Map<String,String> adMap) {
        Ad ad=  new Ad(adMap.get("title"),userService.getCurrentUser());
        adService.add(ad);
        Map map = new HashMap();
        map.put("ad",ad);
        return map;
    }

    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map del(@PathVariable long id) {
        Map map = new HashMap();
        Ad ad = adService.get(id);
        User user = userService.getCurrentUser();

        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            map.put("error", "Authorization error");
        }
        if (ad == null || ad.getId() < 1) {
            map.put("error", "Ad #" + id + " not found");
        } else if (!user.getUsername().equals(ad.getUser().getUsername())) {
            map.put("error", "You don't have permission to delete this");
        } else {
            adService.delete(id);
        }
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
        User user=userService.getCurrentUser();
        Map map = new HashMap();
        List l = adService.list(user, start, length, DataTableUtils.getColumnDirectionList(request), searchText);
        map.put("draw", draw);
        map.put("data", l);
        map.put("recordsTotal", adService.count(user));
        map.put("recordsFiltered", adService.count(user,searchText));
        return map;
    }

    @RequestMapping(value = "info.json")
    @ResponseBody
    public Ad list(@RequestParam(value = "id", required = true) long id) {
        Map map = new HashMap();
        return adService.get(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map update(@RequestBody Map<String,String> adMap) {
        Ad ad=  mapToBeacon(adMap, adService.get(NumberUtils.toLong(adMap.get("id"))));
        adService.update(ad);
        Map map = new HashMap();
        return map;
    }


}
