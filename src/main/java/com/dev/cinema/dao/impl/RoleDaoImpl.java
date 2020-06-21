package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory factory;

    public RoleDaoImpl(SessionFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public Role add(Role role) {
        role = super.add(role);
        LOGGER.info("Role with id " + role.getId() + " was added to DB");
        return role;
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id, Role.class);
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> query = session
                    .createQuery("from Role r "
                            + " where r.roleName =: roleName", Role.class);
            query.setParameter("roleName", Role.RoleName.valueOf(roleName));
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find role with name "
                    + roleName, e);
        }
    }
}
