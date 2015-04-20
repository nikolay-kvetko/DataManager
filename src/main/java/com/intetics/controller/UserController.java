package com.intetics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Кузнец on 19.04.2015.
 */

@Controller
public class UserController {

    @RequestMapping(value = "/registration")
    public String getEntitySchemaList() {

        return "admin-registration";
    }


}
