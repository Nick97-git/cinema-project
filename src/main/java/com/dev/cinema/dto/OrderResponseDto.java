package com.dev.cinema.dto;

import java.util.List;

public class OrderResponseDto {
    private String orderDate;
    private List<TicketDto> tickets;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
