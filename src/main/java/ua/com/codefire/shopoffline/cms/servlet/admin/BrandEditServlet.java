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
import ua.com.codefire.shopoffline.cms.db.controller.BrandController;
import ua.com.codefire.shopoffline.cms.db.entity.Brand;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns="/admin/brand/edit")
public class BrandEditServlet extends HttpServlet{
private BrandController bc;
    @Override
    public void init() throws ServletException {
         bc = new BrandController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Brand brand;
         try {
            brand = bc.findOne(Integer.parseInt(req.getParameter("id")));
            
            if (brand == null) {
                throw new NumberFormatException("Phone not found!");
            }
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath().concat("/admin/brands"));
            return;
        }
          req.setAttribute("foundBrand", brand);
          req.getRequestDispatcher("/WEB-INF/jsp/admin/brand.edit.jsp").forward(req, resp);

        
    }
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String name = req.getParameter("name");
        String country = req.getParameter("country");
       
        Brand brand;
         try {
            brand = bc.findOne(Integer.parseInt(req.getParameter("id")));
            
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }
        
        brand.setName(name);
        brand.setCountry(country);
       
        brand = bc.save(brand);

        resp.sendRedirect(req.getContextPath().concat("/admin/brand/edit?id=" + brand));
    }
 
}
