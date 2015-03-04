package me.shopian.shopian3.controller.api;

import me.shopian.shopian3.dao.UserMobileDaoImpl;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.entity.UserMobile;
import me.shopian.shopian3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/token")
public class TokenController {
    @Qualifier("userDetailsService")
    @Autowired
    private UserServiceImpl userService;
    @Qualifier("userMobileDaoImpl")
    @Autowired
    private UserMobileDaoImpl userMobileDaoImpl;


    /**
     * for testing.
     * curl -X POST --data "productToken=cb9c806465abbde8df83ab9f638824d3"  'http://localhost:8080/api/token/getNew' ;echo
     */

    @RequestMapping(value = "getNew",method = {RequestMethod.POST})
    @ResponseBody
    public Map list(@RequestParam(value = "productToken", required = true) String productToken) {
        Map map = new HashMap();
        User user=userService.findUserByToken(productToken);
        if (user==null){
            map.put("error","productToken '"+productToken+"' not found");
            return map;
        }
        UserMobile userMobile=new UserMobile(user);
        userMobileDaoImpl.add(userMobile);
        map.put("token",userMobile.getToken());
        return map;
    }
}
