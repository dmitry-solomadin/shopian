package me.shopian.shopian3.controller;

import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.ShopServiceImpl;
import me.shopian.shopian3.service.UserService;
import me.shopian.shopian3.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
    private static Logger logger = LoggerFactory.getLogger(RootController.class);

    @Qualifier("userDetailsService")
    @Autowired(required = true)
    private UserService userService;

    @Qualifier("shopServiceImpl")
    @Autowired
    private ShopServiceImpl shopService;

    @RequestMapping(method = RequestMethod.GET, value = {""})
    public String index(Model model) {
        return "index";
    }
    @RequestMapping(method = RequestMethod.GET, value = {"ad"})
    public String ad(Model model) {
        return "ad";
    }
    @RequestMapping(method = RequestMethod.GET, value = {"shop"})
    public String shop(Model model) {
        return "shop";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"beacon"})
    public String beacon(Model model) {
        User user=userService.getCurrentUser();
        model.addAttribute("users",userService.list("ROLE_USER"));
//        model.addAttribute("shops", shopService.list(user));
        return "beacon";
    }
}
