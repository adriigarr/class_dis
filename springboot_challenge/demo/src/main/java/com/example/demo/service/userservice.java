package com.example.demo.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class userservice {
    private static final String FILE_PATH = "src/main/resources/favourites.json";
    private Map<String, List<String>> userFavorites = new HashMap<>();

    public userservice() {
        loadFavorites();
    }

    // Load favorites from favourites.json
    private void loadFavorites() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
            userFavorites = new Gson().fromJson(reader, type);
            if (userFavorites == null) userFavorites = new HashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save favorites to favourites.json
    private void saveFavorites() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(userFavorites, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a favorite movie for a user
    public void addFavorite(String userId, String movieId) {
        userFavorites.computeIfAbsent(userId, k -> new ArrayList<>()).add(movieId);
        saveFavorites();
    }

    // Get all favorite movies for a user
    public List<String> getFavorites(String userId) {
        return userFavorites.getOrDefault(userId, new ArrayList<>());
    }

    // Remove a favorite movie for a user
    public void removeFavorite(String userId, String movieId) {
        if (userFavorites.containsKey(userId)) {
            userFavorites.get(userId).remove(movieId);
            saveFavorites();
        }
    }
}
