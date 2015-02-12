package me.shopian.shopian3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
    private static Logger logger = LoggerFactory.getLogger(RootController.class);

    @RequestMapping(method = RequestMethod.GET, value = {""})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"shop"})
    public String shop(Model model) {
        return "shop";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"beacon"})
    public String beacon(Model model) {
        return "beacon";
    }
}
