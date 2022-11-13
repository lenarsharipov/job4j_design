package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final List<Session> SESSIONS = new ArrayList<>();
    private static final List<Ticket> TICKETS = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> rsl = new ArrayList<>();
        for (Session session : SESSIONS) {
            if (filter.test(session)) {
                rsl.add(session);
            }
        }
        return rsl;
    }

    private boolean isVacant(Ticket ticket) {
        boolean rsl = true;
        for (Ticket ticket2 : TICKETS) {
            if (ticket.equals(ticket2)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row < 1 || row > ROWS || column < 1 || column > COLUMNS) {
            throw new IllegalArgumentException("Illegal passed arguments");
        }
        Ticket ticket = new Ticket3D(account, row, column, date);
        if (!isVacant(ticket)) {
            throw new IllegalArgumentException("The ticket already sold");
        } else {
            TICKETS.add(ticket);
        }
        return ticket;
    }

    @Override
    public void add(Session session) {
        SESSIONS.add(session);
    }
}
