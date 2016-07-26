/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.shopoffline.cms.db.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ua.com.codefire.shopoffline.cms.db.entity.Users;

/**
 *
 * @author user
 */
public class UserController extends DBController {

    public List<Users> getAll() {
        EntityManager manager = getFactory().createEntityManager();

        try {
            TypedQuery<Users> query = manager.createQuery("SELECT u FROM Users u", Users.class);
            List<Users> userList = query.getResultList();
            return userList;
        } finally {
            manager.close();
        }
    }
     public void remove(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {

            Users u = manager.find(Users.class, id);
            if (u != null) {
                manager.getTransaction().begin();

                manager.remove(u);
                manager.getTransaction().commit();
            }
        } finally {
            manager.close();
        }
    }
    
    public long inspectionEmail(String email) {
        EntityManager manager = getFactory().createEntityManager();
        try {
            Query query = manager.createQuery("SELECT COUNT(u) FROM Users u WHERE u.email=:email");
            query.setParameter("email", email);
            long count = (long) query.getSingleResult();
            return count;
        } finally {
            manager.close();
        }

    }

    public Users save(Users user) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            manager.getTransaction().begin();
            if (user.getId() == null || user.getId() < 1) {
                manager.persist(user);
            } else {
                user = manager.merge(user);
            }
            manager.getTransaction().commit();

            return user;
        } finally {
            manager.close();
        }
    }

    public Users findByMailAndPassword(String email, String password) {
        Users user;
            EntityManager manager = getFactory().createEntityManager();
        try {
            Query query = manager.createQuery("SELECT u FROM Users u WHERE u.email=:email AND u.password=:password", Users.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
             user =  (Users) query.getSingleResult();
            return user;
        } finally {
            manager.close();
        }
    }

    public Users findOne(int id) {
        EntityManager manager = getFactory().createEntityManager();

        try {
            return manager.find(Users.class, id);
        } finally {
            manager.close();
        }
    }

}
