package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private final SessionFactory factory;

    public GenericDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public T add(T element) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + element.getClass().getSimpleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected T findById(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get " + clazz.getSimpleName()
                    + " with id = " + id, e);
        }
    }
}
