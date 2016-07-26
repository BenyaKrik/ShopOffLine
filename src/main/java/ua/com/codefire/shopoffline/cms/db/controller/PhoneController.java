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
import ua.com.codefire.shopoffline.cms.db.entity.Brand;
import ua.com.codefire.shopoffline.cms.db.entity.Phone;

/**
 *
 * @author user
 */
public class PhoneController extends DBController {

    public List<Phone> getAll() {
        EntityManager manager = getFactory().createEntityManager();

        try {
            TypedQuery<Phone> query = manager.createQuery("SELECT p FROM Phone p", Phone.class);
            List<Phone> phoneList = query.getResultList();
            return phoneList;
        } finally {
            manager.close();
        }
    }
    
    public List<Phone> brandFilter(int brand_id) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            TypedQuery<Phone> query = manager.createQuery("SELECT p FROM Phone p WHERE p.brand.id = :brand_id", Phone.class);
            query.setParameter("brand_id", brand_id);
            List<Phone> phoneList = query.getResultList();
            return phoneList;
        } finally {
            manager.close();
        }
    }

    public Phone save(Phone phone) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            manager.getTransaction().begin();
            if (phone.getId() == null || phone.getId() < 1) {
                manager.persist(phone);
            } else {
                phone = manager.merge(phone);
            }
            manager.getTransaction().commit();

            return phone;
        } finally {
            manager.close();
        }
    }

    public Phone findOne(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            return manager.find(Phone.class, id);
        } finally {
            manager.close();
        }
    }

    public void remove(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {

            Phone p = manager.find(Phone.class, id);
            if (p != null) {
                manager.getTransaction().begin();

                manager.remove(p);
                manager.getTransaction().commit();
            }
        } finally {
            manager.close();
        }
    }

    public void remove(Iterable<Integer> ids) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            for (Integer id : ids) {
                Phone p = manager.find(Phone.class, id);
                if (p != null) {
                    manager.getTransaction().begin();
                    manager.remove(p);
                    manager.getTransaction().commit();
                }
            }
        } finally {
            manager.close();
        }
    }
}
