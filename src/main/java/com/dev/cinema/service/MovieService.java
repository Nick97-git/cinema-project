package com.dev.cinema.service;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieService extends GenericService<Movie> {

    List<Movie> getAll();
}
