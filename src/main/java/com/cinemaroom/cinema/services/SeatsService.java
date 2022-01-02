package com.cinemaroom.cinema.services;

import com.cinemaroom.cinema.entity.Seat;
import com.cinemaroom.cinema.entity.Token;
import com.cinemaroom.cinema.exceptions.CustomExceptionForBadRequest;
import com.cinemaroom.cinema.exceptions.CustomExceptionForUnauthorized;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class SeatsService {

    private final HashMap<String, Object> infoAboutSeats = new HashMap<>();
    private final HashMap<Token, Integer> withToken = new HashMap<>();
    private int income = 0;

    {
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                seats.add(new Seat(i, j));
            }
        }
        infoAboutSeats.put("total_rows", 9);
        infoAboutSeats.put("total_columns", 9);
        infoAboutSeats.put("available_seats", seats);

    }

    public HashMap<String, Object> getInfoAboutSeats() {
        return infoAboutSeats;
    }

    private List<Seat> getSeat(Map<String, Object> infoAboutSeats) {
        @SuppressWarnings("unchecked")
        ArrayList<Seat> seats = (ArrayList<Seat>) infoAboutSeats.get("available_seats");
        return seats;
    }

    public Map<String, Object> buySeat(Seat seat) throws ResponseStatusException {
        if (seat.getColumn() > 9 || seat.getRow() > 9 || seat.getColumn() < 1
                || seat.getRow() < 1) {
            throw new CustomExceptionForBadRequest("The number of a row or a column is out of bounds!");
        }

        List<Seat> seats = getSeat(infoAboutSeats);
        int index = seats.indexOf(seat);

        if (withToken.containsValue(index)) {
            throw new CustomExceptionForBadRequest("The ticket has been already purchased!");
        }

        Token token = new Token(UUID.randomUUID().toString());
        Seat ticket = seats.get(index);
        withToken.put(token, index);
        income+=seat.getPrice();
        return new HashMap<>(Map.of("token", token.toString(), "ticket", ticket));
    }

    public Map<String, Seat> returnSeat(Token token) {
        try {
            Integer index = withToken.get(token);
            Seat seat = getSeat(infoAboutSeats).get(index);
            income-=seat.getPrice();
            withToken.remove(token);
            return new HashMap<>(Map.of("returned_ticket", seat));
        } catch (NullPointerException e) {
            throw new CustomExceptionForBadRequest("Wrong token!");
        }

    }

    public Map<String, Integer> showStatistics(String password) {
        if (password == null || !password.equals("super_secret")) throw new CustomExceptionForUnauthorized("The password is wrong!");
        return new HashMap<>(Map.of("current_income", income,
                "number_of_available_seats", getSeat(getInfoAboutSeats()).size() - withToken.size(),
                "number_of_purchased_tickets", withToken.size()));
    }
}


