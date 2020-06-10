package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private List<TicketResponseDto> tickets;

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
