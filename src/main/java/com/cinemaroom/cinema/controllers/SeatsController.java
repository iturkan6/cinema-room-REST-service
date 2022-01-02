package com.cinemaroom.cinema.controllers;

import com.cinemaroom.cinema.services.SeatsService;
import com.cinemaroom.cinema.entity.Seat;
import com.cinemaroom.cinema.entity.Token;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SeatsController {

    private final SeatsService service;

    public SeatsController(SeatsService service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public Map<String, Object> getSeats() {
        return service.getInfoAboutSeats();
    }

    @PostMapping("/purchase")
    public Map<String, Object> toPurchase(@RequestBody Seat seat) {
        return service.buySeat(seat);
    }

    @PostMapping("/return")
    public Map<String, Seat> returnTicket(@RequestBody Token token) {
        return service.returnSeat(token);
    }

    @PostMapping(value = "/stats")
    public Map<String, Integer> getStatistics(@RequestParam(value = "password", required = false) String password) {
        return service.showStatistics(password);
    }

}
