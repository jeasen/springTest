package com.ssm.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by mm on 2017/8/15.
 */
public class MyAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint{
    public MyAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    public Map<String,String> authEntryPointMap;
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String requestURI = request.getRequestURI().replace(
                request.getContextPath(), "");
            for (String url : this.authEntryPointMap.keySet()) {
                if (this.pathMatcher.match(url, requestURI)) {
                    return this.authEntryPointMap.get(url);
                }
            }

            return super.determineUrlToUseForThisRequest(request, response, exception);
        }

    public Map<String, String> getAuthEntryPointMap() {return authEntryPointMap;}

    public void setAuthEntryPointMap(Map<String, String> authEntryPointMap) {this.authEntryPointMap = authEntryPointMap;}

    public PathMatcher getPathMatcher() {return pathMatcher;}

    public void setPathMatcher(PathMatcher pathMatcher) {this.pathMatcher = pathMatcher;}
}
