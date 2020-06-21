package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    @Autowired
    public TicketDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Ticket add(Ticket ticket) {
        ticket = super.add(ticket);
        LOGGER.info("Ticket with id " + ticket.getId() + " was added to DB");
        return ticket;
    }

    @Override
    public Ticket findById(Long id) {
        return super.findById(id, Ticket.class);
    }
}
