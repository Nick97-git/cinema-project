package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        Movie secondMovie = new Movie();
        secondMovie.setTitle("Prestige");
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(movie);
        movieService.add(secondMovie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall redHall = new CinemaHall();
        redHall.setCapacity(150);
        redHall.setDescription("red hall");
        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(200);
        blueHall.setDescription("blue hall");
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(redHall);
        cinemaHallService.add(blueHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession sessionOne = new MovieSession();
        sessionOne.setCinemaHall(redHall);
        sessionOne.setMovie(secondMovie);
        sessionOne.setShowTime(LocalDateTime.of(LocalDate.of(2020, 5, 19),
                LocalTime.of(12, 30)));
        MovieSession sessionTwo = new MovieSession();
        sessionTwo.setCinemaHall(blueHall);
        sessionTwo.setMovie(secondMovie);
        sessionTwo.setShowTime(LocalDateTime.of(LocalDate.of(2020, 5, 23),
                LocalTime.of(12, 30)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(sessionOne);
        movieSessionService.add(sessionTwo);
        List<MovieSession> sessions = movieSessionService.findAvailableSessions(secondMovie.getId(),
                LocalDate.of(2020, 5, 20));
        sessions.forEach(System.out::println);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User user = authenticationService.register("email", "password");
        User user2 = authenticationService.register("email2", "password2");
        try {
            authenticationService.login("email", "password");
            authenticationService.login("email2", "password2");
            LOGGER.info("Authentication of user with id " + user.getId() + " was succeed");
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }

        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        cartService.addSession(sessionOne, user2);
        cartService.addSession(sessionTwo, user);
        cartService.addSession(sessionOne, user);
        ShoppingCart cart = cartService.getByUser(user);
        System.out.println("----------------------Cart----------------");
        System.out.println(cart.toString());
        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(cart.getTickets(), user);
        cartService.clear(cart);
        cartService.addSession(sessionTwo, user);
        cart = cartService.getByUser(user2);
        orderService.completeOrder(cart.getTickets(), user2);
        cartService.clear(cart);
        System.out.println("-------------------Order History------------------------------");
        orderService.getOrderHistory(user)
                .forEach(order -> System.out.println(order.toString()));
    }
}
