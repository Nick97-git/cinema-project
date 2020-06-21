package com.dev.cinema.dao;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> getByEmail(String email);
}
