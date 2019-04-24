package com.favorite.service;

import com.weather.service.WeatherController;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@Component
public class FavoriteAssembler implements ResourceAssembler<FavoriteItem, Resource<FavoriteItem>> {

    @Override
    public Resource<FavoriteItem> toResource(FavoriteItem item) {
        return new Resource<>(item, linkTo(methodOn(FavoriteController.class).findFavorite(item.getId())).withSelfRel(),
                                    linkTo(methodOn(WeatherController.class).weather(item.getLocation())).withRel("weather"));
    }
}

