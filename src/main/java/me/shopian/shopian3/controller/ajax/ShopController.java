package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.ShopService;
import me.shopian.shopian3.service.UserService;
import me.shopian.shopian3.util.DataTableUtils;
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
@RequestMapping("/ajax/shop")
public class ShopController {
    private static Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Qualifier("shopServiceImpl")
    @Autowired(required = true)
    private ShopService shopService;
    @Qualifier("userDetailsService")
    @Autowired(required = true)
    private UserService userService;


    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody Shop shop) {
        shop.setUser(userService.getCurrentUser());

        logger.info("Shop controller: " + shop);
        Map map = new HashMap();
        Shop tmp = shopService.getByTitle(shop);
        logger.info("tmp: " + tmp);
        if (tmp == null) {
            shopService.add(shop);
        } else {
            map.put("error", "Магазин '" + shop.getTitle() + "' уже есть ( id:" + tmp.getId() + " )");
        }
        map.put("shop", shop);
        logger.info("shop " + shop);
        return map;
    }

    @RequestMapping(value = "{id:\\d+}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map del(@PathVariable long id) {
        Map map = new HashMap();
        Shop shop = shopService.get(id);
        User user = userService.getCurrentUser();

        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            map.put("error", "Authorization error");
        }
        if (shop == null || shop.getId() < 1) {
            map.put("error", "Shop #" + id + " not found");
        } else if (!user.getUsername().equals(shop.getUser().getUsername())) {
            map.put("error", "You don't have permission to delete this shop");
        } else {
            shopService.delete(id);
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
        Map map = new HashMap();
        List l = shopService.list(userService.getCurrentUser(), start, length, DataTableUtils.getColumnDirectionList(request), searchText);
        map.put("draw", draw);
        map.put("data", l);
        map.put("recordsTotal", shopService.count());
        map.put("recordsFiltered", shopService.count(searchText));
        return map;
    }

    @RequestMapping(value = "info.json")
    @ResponseBody
    public Map list(@RequestParam(value = "id", required = true) long id) {
        Map map = new HashMap();
        Shop shop = shopService.get(id);
        System.out.println("shop = " + shop);
        map.put("id", shop.getId());
        map.put("title", shop.getTitle());
        map.put("address", shop.getAddress());
//        map.put("departments",shop.getDepartments());
        return map;
    }

    @RequestMapping(value = "departments.json")
    @ResponseBody
    public Map departments(
            @RequestParam(value = "id", required = false, defaultValue = "0") long id,
            @RequestParam(value = "draw", required = false, defaultValue = "0") int draw
    ) {
        Shop shop = shopService.get(id);

        Collection list=null;
        if (shop != null) {
            list=shop.getDepartments();
        }
        if (list==null) list=new ArrayList();

        Map map = new HashMap();
        map.put("draw", draw);
        map.put("data", list);
        map.put("recordsTotal", list.size());
        map.put("recordsFiltered", list.size());
        return map;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map update(@RequestBody Shop shop) {
        shop.setUser(userService.getCurrentUser());

        logger.info("Shop update : " + shop);
        Map map = new HashMap();
        Shop tmp = shopService.get(shop.getId());
        tmp.setTitle(shop.getTitle());
        tmp.setAddress(shop.getAddress());
//        logger.info("tmp: " + tmp);
        if (tmp != null) {
            shopService.update(tmp);
        } else {
            map.put("error", "Магазина с id:" + shop.getId() + " не существует");
        }
        return map;
    }


}
