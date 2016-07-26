/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.com.codefire.shopoffline.cms.db.controller.UserController;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/user/remove")
public class UserRemoveServlet extends HttpServlet {

    private UserController uc;

    @Override
    public void init() throws ServletException {
        uc = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }
        uc.remove(id);

        resp.sendRedirect(req.getContextPath().concat("/admin/users"));
    }

}
