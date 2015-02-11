/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthenticationController {
    
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(method = RequestMethod.GET, value = { "login" })
    public String showLoginPage(Model model) {
        return "login";
    }

}
