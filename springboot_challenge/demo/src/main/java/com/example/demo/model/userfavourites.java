package com.example.demo.model;

import java.util.List;

public class userfavourites {
    private String userId;
    private List<String> favourites;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<String> getFavourites() {
        return favourites;
    }
    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }
}
