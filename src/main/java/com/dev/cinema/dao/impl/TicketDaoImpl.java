package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory factory;

    @Autowired
    public TicketDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            LOGGER.info("Ticket with id " + ticket.getId() + " was added to DB");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
