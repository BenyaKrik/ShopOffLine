/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.servlet.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.com.codefire.shopoffline.cms.db.controller.UserController;
import ua.com.codefire.shopoffline.cms.db.entity.Users;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/user/edit")
public class UserEditServlet extends HttpServlet{
private UserController uc;
    @Override
    public void init() throws ServletException {
        uc = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Users user;

        try {
            user = uc.findOne(Integer.parseInt(req.getParameter("id")));
            
            if ( user == null) {
                throw new NumberFormatException("User not found!");
            }
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath().concat("/admin/users"));
            return;
        }

        req.setAttribute("foundUser",  user);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/user.edit.jsp").forward(req, resp);

    
    
    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String status = req.getParameter("status");
         
        
        Users user;

        try {
            user = uc.findOne(Integer.parseInt(req.getParameter("id")));
             
            
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }

        user.setName(name);
        user.setEmail(email);
        user.setStatus(status);
        user = uc.save(user);

        resp.sendRedirect(req.getContextPath().concat("/admin/user/edit?id=" + user));
    }

    
    
}
