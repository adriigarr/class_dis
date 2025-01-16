package com.example.demo.controller;

import com.example.demo.service.movieservice;
import com.example.demo.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private movieservice movieService;

    @Autowired
    private userservice userService;

    @GetMapping("/movies")
    public List<com.example.demo.model.movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/favorites/{userId}/{movieId}")
    public void addFavorite(@PathVariable String userId, @PathVariable String movieId) {
        userService.addFavorite(userId, movieId);
    }

    @GetMapping("/favorites/{userId}")
    public List<String> getFavorites(@PathVariable String userId) {
        return userService.getFavorites(userId);
    }

    @DeleteMapping("/favorites/{userId}/{movieId}")
    public void removeFavorite(@PathVariable String userId, @PathVariable String movieId) {
        userService.removeFavorite(userId, movieId);
    }
}
