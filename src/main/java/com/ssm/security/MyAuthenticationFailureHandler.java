package com.ssm.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mm on 2017/8/15.
 */
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    private MyAuthenticationEntryPoint loginEntry;
    public MyAuthenticationEntryPoint getLoginEntry() {return loginEntry;}


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String authenfailureUrl = this.loginEntry.getLoginFormUrl();
        authenfailureUrl = authenfailureUrl + "?error";
        super.setDefaultFailureUrl(authenfailureUrl);
        super.onAuthenticationFailure(request, response, exception);
        System.out.println("我失败了我失败了我失败了");
    }

    public void setLoginEntry(MyAuthenticationEntryPoint loginEntry) {
        this.loginEntry = loginEntry;
    }
}
