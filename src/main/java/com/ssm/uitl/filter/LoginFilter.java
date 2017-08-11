package com.ssm.uitl.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mm on 2017/8/9.
 */
public class LoginFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤了过滤了过滤前-------");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession(true);

        String url = request.getRequestURI();
        Object obj = session.getAttribute("user");
       /* filterChain.doFilter(servletRequest,servletResponse);*/
       System.out.print("SJKJ"+obj);
       if (obj != null){
            filterChain.doFilter(request,response);
            System.out.println("有路径"+obj);
        }
        System.out.println("---xxxx---");/*else{
            serReponse.sendRedirect("/test/person/userLogin");
        }*/

    }

    @Override
    public void destroy() {

    }
}
