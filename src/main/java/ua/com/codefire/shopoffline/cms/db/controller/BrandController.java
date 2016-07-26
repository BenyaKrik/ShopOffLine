/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.db.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import static ua.com.codefire.shopoffline.cms.db.controller.DBController.getFactory;
import ua.com.codefire.shopoffline.cms.db.entity.Brand;
import ua.com.codefire.shopoffline.cms.db.entity.Phone;

/**
 *
 * @author user
 */
public class BrandController extends DBController {

     public void remove(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {

            Brand b = manager.find(Brand.class, id);
            if (b != null) {
                manager.getTransaction().begin();

                manager.remove(b);
                manager.getTransaction().commit();
            }
        } finally {
            manager.close();
        }
    }
    
    public Brand save(Brand brand) {
        
             EntityManager manager = getFactory().createEntityManager();
            manager.getTransaction().begin();
          try {
              if (brand.getId() == null || brand.getId() < 1) {
                manager.persist(brand);
            } else {
                brand = manager.merge(brand);
            }
            manager.getTransaction().commit();
            return brand;
        } finally {
            manager.close();
        }
    }

    public Brand findOne(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            return manager.find(Brand.class, id);
        } finally {
            manager.close();
        }
    }

    public List<Brand> getAll() {
        EntityManager manager = getFactory().createEntityManager();

        try {
            TypedQuery<Brand> query = manager.createQuery("SELECT b FROM Brand b", Brand.class);
            List<Brand> listBrand = query.getResultList();
            return listBrand;
        } finally {
            manager.close();
        }
    }

}
