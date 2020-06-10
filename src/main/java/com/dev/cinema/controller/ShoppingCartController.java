package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final MovieSessionService movieSessionService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(MovieSessionService movieSessionService,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService) {
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getCartByUser(@RequestParam Long userId) {
        User user = userService.findById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return convertToShoppingCartDto(shoppingCart);
    }

    @PostMapping("/addmoviesession")
    public void add(@RequestParam Long movieSessionId,
                      @RequestParam Long userId) {
        User user = userService.findById(userId);
        MovieSession movieSession = movieSessionService.findById(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    private ShoppingCartResponseDto convertToShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        List<TicketResponseDto> tickets = shoppingCart.getTickets().stream()
                .map(this::convertToTicketDto)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTickets(tickets);
        return shoppingCartResponseDto;
    }

    private TicketResponseDto convertToTicketDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        ticketResponseDto.setHallDescription(ticket.getMovieSession()
                .getCinemaHall().getDescription());
        ticketResponseDto.setShowTime(ticket.getMovieSession().getShowTime().toString());
        return ticketResponseDto;
    }
}
