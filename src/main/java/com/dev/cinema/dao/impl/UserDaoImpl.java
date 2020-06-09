package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory factory;

    @Autowired
    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            LOGGER.info("User with id " + user.getId() + " was added to DB");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder
                    .createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate predicateEmail = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.where(predicateEmail);
            return session.createQuery(criteriaQuery).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user with email "
                    + email, e);
        }
    }

    @Override
    public User getById(Long userId) {
        try (Session session = factory.openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user with id = " + userId, e);
        }
    }
}
