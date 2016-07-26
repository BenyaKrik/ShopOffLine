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
import ua.com.codefire.shopoffline.cms.db.entity.Brand;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/brands")
public class BrandsServlet extends HttpServlet {

    private BrandController bc;

    @Override
    public void init() throws ServletException {
        bc = new BrandController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Brand> bandAll = bc.getAll();
        req.setAttribute("brandList", bandAll);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/brands.jsp").forward(req, resp);

    }

}
