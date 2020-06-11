package com.dev.cinema.controller;

import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final MovieSessionService movieSessionService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ShoppingCartMapper cartMapper;

    public ShoppingCartController(MovieSessionService movieSessionService,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  ShoppingCartMapper cartMapper) {
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.cartMapper = cartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getCartByUser(@RequestParam Long userId) {
        User user = userService.findById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return cartMapper.convertToResponseDto(shoppingCart);
    }

    @PostMapping
    public void add(@RequestParam Long movieSessionId,
                      @RequestParam Long userId) {
        User user = userService.findById(userId);
        MovieSession movieSession = movieSessionService.findById(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }
}
