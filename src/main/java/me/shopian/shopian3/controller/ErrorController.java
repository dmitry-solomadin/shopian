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


@Controller
@RequestMapping("error")
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = {"404"})
    public String error404(Model model) {
        logger.debug("Page Request: /error/404");
        return "error/404";
    }

    @RequestMapping(value = {"403"})
    public String error403(Model model) {
        logger.debug("Page Request: /error/403");
        return "error/403";
    }

    @RequestMapping(value = {"500"})
    public String error500(Model model) {
        logger.debug("Page Request: /error/500");
        return "error/500";
    }

}