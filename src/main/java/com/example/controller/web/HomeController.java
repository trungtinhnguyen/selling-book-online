package com.example.controller.web;

import com.example.util.MessageUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller ("webHomeController")
public class HomeController {

    @RequestMapping (value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage () {
        return new ModelAndView("web/home");
    }

    @RequestMapping (value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav =  new ModelAndView("auth/login");
        if (request.getParameter("message") != null) {
            Map<String, String> message = new MessageUtils().loadMessage(request.getParameter("message"));
            mav.addObject("message", message.get(MessageUtils.MESSAGE));
            mav.addObject("alert", message.get(MessageUtils.TYPE));
        }

        return mav;
    }

    @RequestMapping (value = "/dang-xuat", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ModelAndView("redirect:/trang-chu");
    }

    @RequestMapping (value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied () {
        return new ModelAndView("redirect:/dang-nhap?message=access_denied");
    }

    @RequestMapping (value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("auth/register");
    }
}
