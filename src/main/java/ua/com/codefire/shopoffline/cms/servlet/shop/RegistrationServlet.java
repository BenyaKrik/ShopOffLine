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
import ua.com.codefire.shopoffline.cms.db.controller.UserController;
import ua.com.codefire.shopoffline.cms.db.entity.Users;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private UserController uc;

    @Override
    public void init() throws ServletException {
        uc = new UserController();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = null;
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (uc.inspectionEmail(email) < 1) {
            if (password.equals(password2)) {

                Users user = new Users(name, email, password);
                user = uc.save(user);
                req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

            } else {
                message = "разные пароли";

            }
        } else {
            message = "Емейл уже существует";

        }
        req.setAttribute("message", message);

        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

}
