/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String user = (String) req.getSession().getAttribute("status");
        if (user == null) {
            req.getSession().setAttribute("referer", req.getServletPath());
            
            req.getRequestDispatcher("/auth").forward(request, response);
        } else if ("admin".equals(user)) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(403, "Forbidden");
        }

    }

    @Override
    public void destroy() {

    }

}
