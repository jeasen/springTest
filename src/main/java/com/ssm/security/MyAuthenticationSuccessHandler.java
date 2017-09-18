package com.ssm.security;

import com.ssm.dto.Person;
import com.ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by mm on 2017/8/15.
 * 如果是成功了就跳到这个页面上来
 */
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
       private Map<String,String> authDispatcherMap;
       private RequestCache requestCache = new HttpSessionRequestCache();

       @Autowired
       private PersonService personService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        Collection<? extends GrantedAuthority> authCollection = authentication.getAuthorities();
        if(authCollection.isEmpty()){
            return;
        }

        //获得信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Person user = new Person();
        System.out.println("user123"+userDetails.getUsername()+","+userDetails.getPassword());
        user.setName(userDetails.getUsername());
        request.getSession().setAttribute("user", user);
        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            url = savedRequest.getRedirectUrl();
        }

        if (url == null) {
            for (GrantedAuthority auth : authCollection) {
                url = authDispatcherMap.get(auth.getAuthority());
            }
            getRedirectStrategy().sendRedirect(request, response, url);
        }

    }

    public Map<String, String> getAuthDispatcherMap() {return authDispatcherMap;}

    public void setAuthDispatcherMap(Map<String, String> authDispatcherMap) {this.authDispatcherMap = authDispatcherMap;}

    public RequestCache getRequestCache() {return requestCache;}

    @Override
    public void setRequestCache(RequestCache requestCache) {this.requestCache = requestCache;}


}
