package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.getRoleByName("USER");
        user.setRoles(Set.of(role));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
