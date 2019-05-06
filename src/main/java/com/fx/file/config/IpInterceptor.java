package com.fx.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class IpInterceptor implements HandlerInterceptor {

    @Value("#{'${ipList}'.split(',')}")
    private List<String> ipList;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(ipList.contains(request.getRemoteAddr()))
            return true;
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            return false;

        }
    }
}
