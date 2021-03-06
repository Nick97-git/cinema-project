package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory factory;

    @Autowired
    public OrderDaoImpl(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public Order add(Order order) {
        order = super.add(order);
        LOGGER.info("Order with id " + order.getId() + " was added to DB");
        return order;
    }

    @Override
    public Order getById(Long id) {
        return super.getById(id, Order.class);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = factory.openSession()) {
            Query<Order> query = session
                    .createQuery("select distinct o from Order o "
                            + "left join fetch o.tickets Ticket "
                            + "where o.user =: user", Order.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find orders of user with id "
                    + user.getId(), e);
        }
    }
}
