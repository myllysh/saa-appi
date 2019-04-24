package com.favorite.service;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class FavoriteItem {

    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private String location;

    FavoriteItem(String location) {
        this.location = location;
    }

    public FavoriteItem() {}
}
