/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.db.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class DBController {

    private static EntityManagerFactory factory;

    public synchronized static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("MainPU");
        }

        return factory;
    }
    
}
