package com.blog.controller;

import com.blog.model.AdminLoginLog;
import com.blog.service.impl.AdminLoginLogServiceImpl;
import com.blog.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;


@Controller

public class LoginController {
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    AdminLoginLogServiceImpl adminLoginLogService;
    @RequestMapping(value = {"/admin"})
    public String toIndex(HttpServletRequest request) {

        return "admin/login";


    }

    // 0:用户不存在  1:密码错误 2:登陆成功
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request,HttpServletResponse httpServletResponse) {
        int id=Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("password");
        HashMap<String, String> res = new HashMap<String, String>();
        if(adminService.getById(id)==null){
            res.put("stateCode", "0");
        }
        else if(!adminService.getById(id).getPassword().equals(passwd)){
            res.put("stateCode", "1");
        }else {
            String ip=request.getRemoteAddr();
            AdminLoginLog adminLoginLog=new AdminLoginLog();
            adminLoginLog.setAdminId(id);
            adminLoginLog.setDate(new Date());
            adminLoginLog.setIp(ip);
            int log=adminLoginLogService.insert(adminLoginLog);
            Cookie cookie = new Cookie("userId",""+id);
            cookie.setMaxAge(3600*24);
            httpServletResponse.addCookie(cookie);
            request.getSession().setAttribute("admin",adminService.getById(id));
            res.put("stateCode", "2");
        }
    return res;
    }

    /**
     * 测试使用拦截器
     * @param request
     * @param httpServletResponse
     * @return
     */
    // 0:用户不存在  1:密码错误 2:登陆成功
    @RequestMapping(value = "/api/loginCheck2", method = RequestMethod.POST)
    public ModelAndView loginCheck2(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        ModelAndView mav = new ModelAndView();
        int id=Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("password");
        HashMap<String, String> res = new HashMap<String, String>();
        if(adminService.getById(id)==null){
            res.put("stateCode", "0");
            mav.setViewName("/admin/login");
        }
        else if(!adminService.getById(id).getPassword().equals(passwd)){
            res.put("stateCode", "1");
            mav.setViewName("/admin/login");
        }else {
            String ip=request.getRemoteAddr();
            AdminLoginLog adminLoginLog=new AdminLoginLog();
            adminLoginLog.setAdminId(id);
            adminLoginLog.setDate(new Date());
            adminLoginLog.setIp(ip);
            int log=adminLoginLogService.insert(adminLoginLog);
            Cookie cookie = new Cookie("userId",""+id);
            cookie.setMaxAge(3600*24);
            httpServletResponse.addCookie(cookie);
            request.getSession().setAttribute("admin",adminService.getById(id));
            res.put("stateCode", "2");
            mav.setViewName("/admin/main");
        }
        return mav;
    }

    @RequestMapping(value = {"/admin/logout"})
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("admin");
        return "redirect:/admin";

    }

}
