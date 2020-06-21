package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService extends GenericService<User> {

    User getByEmail(String email);
}
