package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class HibernateProductServiceImpl implements IProductService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        String queryStr = "SELECT p FROM Product AS p";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public Product save(Product product) {
        Transaction transaction = null;
        Product origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (product.getId() != 0) {
                origin = findById(product.getId());
                origin.setName(product.getName());
                origin.setPrice(product.getPrice());
                origin.setDescription(product.getDescription());
                origin.setImage(product.getImage());
            } else {
                origin = product;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product remove(int id) {
        Transaction transaction = null;
        Product origin = findById(id);
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            if (origin != null) {
                session.delete(origin);
            }
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
