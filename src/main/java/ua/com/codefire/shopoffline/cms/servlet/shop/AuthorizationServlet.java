/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.servlet.shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.codefire.shopoffline.cms.db.controller.UserController;
import ua.com.codefire.shopoffline.cms.db.entity.Users;

/**
 *
 * @author user
 *
 */
@WebServlet(urlPatterns = "/auth")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            req.getSession().invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users user;
        UserController uc = new UserController();
        user = uc.findByMailAndPassword(username, password);
        
        if (user.getId() != null || user.getStatus().equals("admin")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getName());
            session.setAttribute("status", user.getStatus());

            doGet(req, resp);
            return;
        }

        if (req.getSession().getAttribute("referer") != null) {
            resp.sendRedirect((String) req.getSession().getAttribute("referer"));
        } else {
            resp.sendRedirect(req.getContextPath().concat("/showcase"));
        }

    }

}
