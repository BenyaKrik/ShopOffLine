/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.listener;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println("request: " + req.getServletPath());
        try {
            // request ->
            sre.getServletRequest().setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("response");
        try {
            // response <-
            sre.getServletRequest().setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RequestListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
