package com.ssm.LoginInterceptor;


import com.ssm.dto.Person;
import com.ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mm on 2017/8/2.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private PersonService personService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestUrl = httpServletRequest.getRequestURI();
        System.out.print("我来咯-----------------------！");
        if(requestUrl.indexOf("/person")>0){
            HttpSession session = httpServletRequest.getSession();
            String username = (String)session.getAttribute("username");
            System.out.println("我收到的是"+username);
            if(username != null){
                System.out.println("------你走吧-----");
                return true;
            }else {
                httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
                System.out.println("------不准走-----");
                return  false;
            }
        }else {
            return  false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
