package com.dev.cinema.controller;

import com.dev.cinema.mapper.MovieSessionMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getMovieSessions(@RequestParam Long movieId,
                                                          @RequestParam LocalDate date) {
        List<MovieSession> availableSessions = movieSessionService
                .getAvailableSessions(movieId, date);
        return availableSessions.stream()
                .map(movieSessionMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.convertToMovieSession(movieSessionRequestDto));
    }
}
