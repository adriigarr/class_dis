package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.service.userservice;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class userserviceTest {

    private userservice userService;

    @BeforeEach
    void setUp() {
        // Initialize the service before each test
        userService = new userservice();
    }

    @Test
    void addFavorite_shouldAddMovieToUserFavorites() {
        // Arrange
        String userId = "1";
        String movieId = "5";

        // Act
        userService.addFavorite(userId, movieId);

        // Assert
        List<String> favorites = userService.getFavorites(userId);
        assertNotNull(favorites);
        assertTrue(favorites.contains(movieId));
    }

    @Test
    void getFavorites_shouldReturnEmptyListForNonExistingUser() {
        // Arrange
        String userId = "999";

        // Act
        List<String> favorites = userService.getFavorites(userId);

        // Assert
        assertNotNull(favorites);
        assertTrue(favorites.isEmpty());
    }

    @Test
    void removeFavorite_shouldRemoveMovieFromFavorites() {
        // Arrange
        String userId = "1";
        String movieId = "5";
        userService.addFavorite(userId, movieId);

        // Act
        userService.removeFavorite(userId, movieId);

        // Assert
        List<String> favorites = userService.getFavorites(userId);
        assertNotNull(favorites);
        assertFalse(favorites.contains(movieId));
    }

    @Test
    void removeFavorite_shouldDoNothingIfMovieNotInFavorites() {
        // Arrange
        String userId = "1";
        String movieId = "999";

        // Act
        userService.removeFavorite(userId, movieId);

        // Assert
        List<String> favorites = userService.getFavorites(userId);
        assertNotNull(favorites);
    }
}

