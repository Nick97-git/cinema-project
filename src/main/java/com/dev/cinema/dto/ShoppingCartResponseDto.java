package com.dev.cinema.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userId;
    private List<TicketDto> tickets;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
