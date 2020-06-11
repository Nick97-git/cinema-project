package com.dev.cinema.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto convertToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        List<TicketResponseDto> tickets = shoppingCart.getTickets().stream()
                .map(ticketMapper::convertToResponseDto)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTickets(tickets);
        return shoppingCartResponseDto;
    }
}
