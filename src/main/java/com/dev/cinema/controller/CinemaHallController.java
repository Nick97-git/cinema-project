package com.dev.cinema.controller;

import com.dev.cinema.dto.CinemaHallDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<CinemaHallDto> getCinemaHalls() {
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        return cinemaHalls.stream()
                .map(this::convertToHallDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public String addMovie(@RequestBody CinemaHallDto cinemaHallDto) {
        cinemaHallService.add(convertToHall(cinemaHallDto));
        return "CinemaHall was successful added";
    }

    private CinemaHallDto convertToHallDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        return cinemaHallDto;
    }

    private CinemaHall convertToHall(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallDto.getCapacity());
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        return cinemaHall;
    }
}
