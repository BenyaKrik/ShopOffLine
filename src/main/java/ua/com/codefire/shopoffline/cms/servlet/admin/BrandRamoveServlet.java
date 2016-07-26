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

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = "/admin/brand/remove")

public class BrandRamoveServlet extends HttpServlet {

    private BrandController bc;

    @Override
    public void init() throws ServletException {

        bc = new BrandController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int brand_id;
        try {
             brand_id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            resp.sendError(400);
            return;
        }
          bc.remove(brand_id);
          

        resp.sendRedirect(req.getContextPath().concat("/admin/brands"));
    
    
    }
    

}
