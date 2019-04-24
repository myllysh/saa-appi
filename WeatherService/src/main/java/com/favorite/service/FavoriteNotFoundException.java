package com.favorite.service;

public class FavoriteNotFoundException extends RuntimeException {

    FavoriteNotFoundException(Integer id) {
        super("Could not find favorite with id " + id);
    }
}
