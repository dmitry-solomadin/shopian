/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.controller;

import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

    /**
     * The logger instance
     **/
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    @Qualifier("userDetailsService")
    @Autowired
    private UserServiceImpl userService;


    /**
     * The instance of userDetailsService injected with Spring
     **/


    /**
     * Handles requests to the /welcome.do page
     **/
    @RequestMapping(method = RequestMethod.GET, value = { "" })
    public String showWelcomePage(Model model) {
//        logger.debug("Page Request: /welcome.do");
//        User user = userService.getCurrentUser();
//        model.addAttribute("name", user.getUsername());
        return "index";
    }
    
    /**
     * Handles requests to the /admin/welcome.do page
     **/
    @RequestMapping(method = RequestMethod.GET, value = { "admin/welcome" })
    public String showAdminWelcomePage(Model model) {
        logger.debug("Page Request: /admin/welcome.do");
        User user = userService.getCurrentUser();
        model.addAttribute("name", user.getUsername());
        return "admin.welcome";
    }
    @RequestMapping(method = RequestMethod.GET, value = { "dashboard" })
    public String showRoot(Model model) {
        logger.debug("Page Request: /dashboard.do");
//        System.out.println("map = " + map);
        return "dashboard";
    }
    @RequestMapping(method = RequestMethod.GET, value = { "aa" })
    public String aa(Model model) {
        logger.debug("Page Request: /aa.do");
//        System.out.println("map = " + map);
        return "aa";
    }
    @RequestMapping(method = RequestMethod.GET, value = { "testJSON" })
    public String o(Model model) {
        logger.debug("Page Request: /testJSON");
//        System.out.println("map = " + map);
        return "testJSON";
    }

}