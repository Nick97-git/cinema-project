package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieDto;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(this::convertToMovieDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieDto movieDto) {
        movieService.add(convertToMovie(movieDto));
        return "Movies was successful added";
    }

    private Movie convertToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }

    private MovieDto convertToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }
}
