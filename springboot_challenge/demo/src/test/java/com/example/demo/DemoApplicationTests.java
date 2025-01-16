package com.example.demo;

import com.example.demo.model.movie;
import com.example.demo.service.movieservice;
import com.example.demo.service.userservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	private movieservice movieService;
	private userservice userService;

	@BeforeEach
	void setUp() {
		movieService = new movieservice(); // Initialize MovieService
		userService = new userservice();   // Initialize UserService
	}

	@Test
	void contextLoads() {
		assertTrue(true); // Placeholder test for Spring context
	}

	// MovieService Tests
	@Test
	void getAllMovies_shouldReturnListOfMovies() {
		List<movie> movies = movieService.getAllMovies();
		assertNotNull(movies);
		assertFalse(movies.isEmpty());
	}

	@Test
	void getAllMovies_shouldContainSpecificMovie() {
		List<movie> movies = movieService.getAllMovies();
		assertTrue(
			movies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase("Inception")),
			"The list of movies should contain 'Inception'."
		);
	}

	// UserService Tests
	@Test
	void addFavorite_shouldAddMovieToUserFavorites() {
		String userId = "1";
		String movieId = "5";
		userService.addFavorite(userId, movieId);

		List<String> favorites = userService.getFavorites(userId);
		assertNotNull(favorites);
		assertTrue(favorites.contains(movieId));
	}

	@Test
	void getFavorites_shouldReturnEmptyListForNonExistingUser() {
		String userId = "999";
		List<String> favorites = userService.getFavorites(userId);

		assertNotNull(favorites);
		assertTrue(favorites.isEmpty());
	}

	@Test
	void removeFavorite_shouldRemoveMovieFromFavorites() {
		String userId = "1";
		String movieId = "5";
		userService.addFavorite(userId, movieId);

		userService.removeFavorite(userId, movieId);

		List<String> favorites = userService.getFavorites(userId);
		assertNotNull(favorites);
		assertFalse(favorites.contains(movieId));
	}

	@Test
	void removeFavorite_shouldDoNothingIfMovieNotInFavorites() {
		String userId = "1";
		String movieId = "999";

		userService.removeFavorite(userId, movieId);

		List<String> favorites = userService.getFavorites(userId);
		assertNotNull(favorites);
	}
}
