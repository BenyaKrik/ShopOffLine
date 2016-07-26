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
import ua.com.codefire.shopoffline.cms.db.controller.PhoneController;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/phone/remove")
public class PhoneRemoveServlet extends HttpServlet {

    private PhoneController pc;

    @Override
    public void init() throws ServletException {
        pc = new PhoneController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            pc.remove(Integer.parseInt(req.getParameter("brand_id")));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }

        resp.sendRedirect(req.getContextPath().concat("/admin/dashboard"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("select");

        for (String id : ids) {

            try {
                pc.remove(Integer.parseInt(id));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                resp.sendError(400);
                return;
            }

        }

        resp.sendRedirect(req.getContextPath().concat("/admin/dashboard"));
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      
//    }
}
