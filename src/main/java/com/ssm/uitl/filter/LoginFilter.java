package com.ssm.uitl.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mm on 2017/8/9.
 */
public class LoginFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("a;a;a;a;a");
        HttpServletRequest serRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse serReponse = (HttpServletResponse)servletResponse;
        String url = serRequest.getRequestURI();
        Object obj = serRequest.getSession().getAttribute("user");
       // filterChain.doFilter(serRequest,serReponse);

        HttpResponseWrapper requestWrapper = new HttpResponseWrapper(serReponse);

        System.out.print("SJKJ"+obj);
         if (obj != null){
        filterChain.doFilter(serRequest,serReponse);
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
class HttpResponseWrapper extends HttpServletResponseWrapper {
    private String[] str = {"hao","ll"};

    public HttpResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return super.getWriter();
    }

/* @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        System.out.println("sssValue"+value);
        if (value != null){
            for (String str1 : str) {
                if (value.contains(str1)){
                    value.replace(str1,"*");
                }
            }
        }
        return value;
    }*/
}
