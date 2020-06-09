package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderRequestDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.TicketDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersByUser(@RequestParam Long userId) {
        User user = userService.getById(userId);
        List<Order> orders = orderService.getOrderHistory(user);
        return orders.stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.getById(orderRequestDto.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        return "Order was successfully completed";
    }

    private OrderResponseDto convertToOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        List<TicketDto> tickets = order.getTickets().stream()
                .map(this::convertToTicketDto)
                .collect(Collectors.toList());
        orderResponseDto.setTickets(tickets);
        return orderResponseDto;
    }

    private TicketDto convertToTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        ticketDto.setHallId(ticket.getMovieSession().getCinemaHall().getId());
        ticketDto.setShowTime(ticket.getMovieSession().getShowTime().toString());
        return ticketDto;
    }
}
