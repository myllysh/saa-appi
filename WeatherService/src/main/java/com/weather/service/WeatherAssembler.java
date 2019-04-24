package com.weather.service;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@Component
public class WeatherAssembler implements ResourceAssembler<WeatherItem, Resource<WeatherItem>> {

    @Override
    public Resource<WeatherItem> toResource(WeatherItem item) {
        return new Resource<>(item, linkTo(methodOn(WeatherController.class).weather(item.getLocation())).withSelfRel());
    }
}

