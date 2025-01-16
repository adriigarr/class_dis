package com.example.demo;

import com.example.demo.model.movie;
import com.example.demo.service.movieservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class movieserviceTest {

    private movieservice movieService;

    @BeforeEach
    void setUp() {
        // Initialize the service before each test
        movieService = new movieservice();
    }

    @Test
    void getAllMovies_shouldReturnListOfMovies() {
        // Act
        List<movie> movies = movieService.getAllMovies();

        // Assert
        assertNotNull(movies);
        assertFalse(movies.isEmpty()); // Assuming the CSV file is not empty
    }

    @Test
    void getAllMovies_shouldContainSpecificMovie() {
        // Act
        List<movie> movies = movieService.getAllMovies();

        // Assert
        assertTrue(
            movies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase("Inception")),
            "The list of movies should contain 'Inception'."
        );
    }
}
