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
@WebServlet(urlPatterns = "/admin/users")
public class UserShowServlet extends HttpServlet{
private UserController uc;
    @Override
    public void init() throws ServletException {
        uc = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> userAll = uc.getAll();
        req.setAttribute("UserList", userAll);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/users.jsp").forward(req, resp);
    }
    
    
}
