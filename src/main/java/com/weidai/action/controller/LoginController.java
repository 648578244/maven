package com.weidai.action.controller;

import com.weidai.action.model.UserBean;
import com.weidai.action.service.ILoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fuck on 17/1/6.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private ILoginService loginServiceImpl;


    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest req, UserBean user) {
        log.info(user);

        ModelAndView mv = new ModelAndView();
        UserBean u = loginServiceImpl.login(user.getUsername(), user.getPassword());

        if (u != null) {

            req.getSession().setAttribute("user", u);
            mv.addObject("password", u.getPassword());
            System.out.println(u.getPassword());
        }
        mv.setViewName("index");
        return mv;
    }
}
