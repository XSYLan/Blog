package com.blog.Interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("---------preHandle--------");
        String ID2 = request.getParameter("id");
        if (ID2==null) {
            //回到登陆页面
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("---------postHandle2--------");
//        modelAndView.setViewName("/admin/login");
        System.out.println("---------我被拦截了--------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("---------afterCompletion--------");
    }

}