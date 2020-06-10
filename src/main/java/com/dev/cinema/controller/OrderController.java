package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
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
        User user = userService.findById(userId);
        List<Order> orders = orderService.getOrderHistory(user);
        return orders.stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.findById(orderRequestDto.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    private OrderResponseDto convertToOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(this::convertToTicketDto)
                .collect(Collectors.toList());
        orderResponseDto.setTickets(tickets);
        return orderResponseDto;
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
