package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectDataController {
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(RoleService roleService,
                                ShoppingCartService shoppingCartService,
                                UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/inject")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRoles(Set.of(adminRole));
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(Set.of(userRole));
        userService.add(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(admin);
        shoppingCartService.registerNewShoppingCart(user);
        return "Test data was added to DB!";
    }
}
