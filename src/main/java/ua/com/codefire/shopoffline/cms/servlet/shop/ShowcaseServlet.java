/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.servlet.shop;

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
@WebServlet(urlPatterns = {"/showcase"})
public class ShowcaseServlet extends HttpServlet {

    private PhoneController pc;
    private BrandController bc;

    @Override
    public void init() throws ServletException {
        pc = new PhoneController();
        bc = new BrandController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int brand_id;
        if (req.getParameter("brand_id") != null) {
            try {

                brand_id = Integer.parseInt(req.getParameter("brand_id"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                resp.sendError(400);
                return;
            }

            List<Phone> bF = pc.brandFilter(brand_id);
            req.setAttribute("phoneList", bF);
        } else {
            List<Phone> all = pc.getAll();
            req.setAttribute("phoneList", all);
        }
        List<Brand> bandAll = bc.getAll();
        req.setAttribute("brandList", bandAll);
        req.getRequestDispatcher("/WEB-INF/jsp/showcase.jsp").forward(req, resp);

    }

}
