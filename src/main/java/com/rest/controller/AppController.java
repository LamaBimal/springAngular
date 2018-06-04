package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bimal on 2/17/18.
 */
@Controller
public class AppController {

    @RequestMapping("/")
    String home(ModelMap modal){
        modal.addAttribute("title","Simple CRUD Application");
        return "index";
    }

    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page){
        return page;
    }
}
