package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends GenericDaoImpl<CinemaHall> implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallDaoImpl.class);
    private final SessionFactory factory;

    @Autowired
    public CinemaHallDaoImpl(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        cinemaHall = super.add(cinemaHall);
        LOGGER.info("Cinema hall with id " + cinemaHall.getId() + " was added to DB");
        return cinemaHall;
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = factory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of all cinema halls ", e);
        }
    }

    @Override
    public CinemaHall getById(Long hallId) {
        return super.getById(hallId, CinemaHall.class);
    }
}
