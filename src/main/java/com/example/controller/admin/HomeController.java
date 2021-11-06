package com.example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller ("adminHomeController")
public class HomeController {

    @RequestMapping (value = "/quan-tri")
    public ModelAndView showDashboard () {
        String view = "admin/home";
        ModelAndView mav = new ModelAndView(view);
        return mav;
    }
}
