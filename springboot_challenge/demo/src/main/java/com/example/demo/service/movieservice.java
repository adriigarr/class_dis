package com.example.demo.service;

import com.example.demo.model.movie;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class movieservice {
    private List<movie> movies = new ArrayList<>();

    public movieservice() {
        loadMovies();
    }

    private void loadMovies() {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("movies.csv")))) {

            String[] line;

            // Skip the header row
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                if (line.length >= 7) { // Ensure the line has enough columns
                    movie movieObj = new movie();
                    movieObj.setId(line[0]);
                    movieObj.setTitle(line[1]);
                    movieObj.setDirector(line[2]);
                    movieObj.setYear(line[3]);
                    movieObj.setGenre(line[4]);
                    movieObj.setRuntime(line[5]);
                    movieObj.setRating(line[6]);
                    movies.add(movieObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<movie> getAllMovies() {
        return movies;
    }
}
