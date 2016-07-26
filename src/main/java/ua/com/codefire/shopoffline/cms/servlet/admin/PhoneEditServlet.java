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
import ua.com.codefire.shopoffline.cms.db.controller.BrandController;
import ua.com.codefire.shopoffline.cms.db.controller.PhoneController;
import ua.com.codefire.shopoffline.cms.db.entity.Brand;
import ua.com.codefire.shopoffline.cms.db.entity.Phone;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/phone/edit")

public class PhoneEditServlet extends HttpServlet {

    private BrandController bc;
    private PhoneController pc;

    @Override
    public void init() throws ServletException {
        pc = new PhoneController();
        bc = new BrandController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Phone phone;

        try {
            phone = pc.findOne(Integer.parseInt(req.getParameter("id")));
            
            if (phone == null) {
                throw new NumberFormatException("Phone not found!");
            }
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath().concat("/admin/dashboard"));
            return;
        }

        req.setAttribute("foundPhone", phone);
        List<Brand> all = bc.getAll();
        req.setAttribute("brandList", all);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/phone.edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int brand_id;
        String model = req.getParameter("model");
        String body = req.getParameter("body");
        double cost;
        
        Phone phone;

        try {
            phone = pc.findOne(Integer.parseInt(req.getParameter("id")));
            brand_id = Integer.parseInt(req.getParameter("brand_id"));
            cost = Double.parseDouble(req.getParameter("cost"));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }

        phone.setModel(model);
        phone.setBody(body);
        phone.setCost(cost);
        phone.setBrand(bc.findOne(brand_id));
        phone = pc.save(phone);

        resp.sendRedirect(req.getContextPath().concat("/admin/phone/edit?id=" + phone));
    }

}
