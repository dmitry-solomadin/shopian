package me.shopian.shopian3.controller.ajax;

import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.DepartmentServiceImpl;
import me.shopian.shopian3.service.ShopService;
import me.shopian.shopian3.service.UserService;
import me.shopian.shopian3.util.DataTableUtils;
import me.shopian.shopian3.util.IdTitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/ajax/department")
public class DepartmentController {
    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Qualifier("shopServiceImpl")
    @Autowired(required = true)
    private ShopService shopService;
    @Qualifier("userDetailsService")
    @Autowired(required = true)
    private UserService userService;
    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentServiceImpl departmentService;


    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map add(@RequestBody IdTitle idTitle) {
        departmentService.add(idTitle.getId(), idTitle.getTitle());
        Map map = new HashMap();
        return map;
    }

    @RequestMapping(value = "{departmentId:\\d+}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map del( @PathVariable long departmentId) {
        departmentService.del(departmentId);
        Map map = new HashMap();
        return map;
    }
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map update(@RequestBody IdTitle idTitle) {
        departmentService.update(idTitle.getId(),idTitle.getTitle());
        Map map = new HashMap();
        return map;
    }
}
