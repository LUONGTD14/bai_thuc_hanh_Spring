package com.linh.hotelbk.utils;

import com.linh.hotelbk.service.impl.UserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String urltarget = getUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, urltarget);
    }

    public String getUrl(Authentication authentication) {
        String url = "/login?error";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority i: authorities) {
            if(i.getAuthority().equals("ROLE_USER"))
                url = "/trang-chu";
            else if (i.getAuthority().equals("ROLE_ADMIN")) {
                url = "/admin/trang-chu";
                break;
            }
        }
        return url;
    }
}
