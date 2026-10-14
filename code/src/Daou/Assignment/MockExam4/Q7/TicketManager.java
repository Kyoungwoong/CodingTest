package Daou.Assignment.MockExam4.Q7;

import java.util.*;

public class TicketManager {

    private final PriorityQueue<Ticket> ticketList = new PriorityQueue<>((a, b) -> {
        if (a.priority() == b.priority()) {
            return Long.compare(a.createdOrder(), b.createdOrder());
        }
        return Integer.compare(b.priority(), a.priority());
    });
    private final Set<String> containsSet = new HashSet<>();

    public boolean addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("ticket not null");
        }
        if (ticket.ticketId() == null || ticket.ticketId().isBlank()) {
            throw new IllegalArgumentException("ticket id not null and zero");
        }
        if (ticket.customerName() == null || ticket.customerName().isBlank()) {
            throw new IllegalArgumentException("customerName not null and zero");
        }
        if (ticket.priority() < 1 || ticket.priority() > 5) {
            throw new IllegalArgumentException("priority between 1 and 5");
        }
        if (ticket.createdOrder() < 0) {
            throw new IllegalArgumentException("createdOrder must be zero and positive number");
        }
        containsSet.add(ticket.ticketId());
        return ticketList.add(ticket);
    }

    public Ticket processNext() {
        Ticket nextTicket = ticketList.poll();
        containsSet.remove(nextTicket.ticketId());
        return nextTicket;
    }

    public Ticket peekNext() {
        return ticketList.peek();
    }

    public int getPendingCount() {
        return ticketList.size();
    }

    public List<Ticket> getPendingTickets() {
        return ticketList.stream().sorted((a,b) -> {
            if (a.priority() == b.priority()) {
                return Long.compare(a.createdOrder(), b.createdOrder());
            }
            return Integer.compare(b.priority(), a.priority());
        }).toList();
    }

    public boolean containsTicket(String ticketId) {
        if (ticketId == null || ticketId.isBlank()) {
            throw new IllegalArgumentException("ticket id not null and zero");
        }
        return containsSet.contains(ticketId);
    }
}