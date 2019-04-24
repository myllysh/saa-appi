package com.favorite.service;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RestController
public class FavoriteController {

    private final FavoriteRepository repo;
    private final FavoriteAssembler assembler;

    FavoriteController(FavoriteRepository repository, FavoriteAssembler assembler) {
        this.repo = repository;
        this.assembler = assembler;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @GetMapping("/favorites")
    Resources<Resource<FavoriteItem>> favorites() {
        List<Resource<FavoriteItem>> all = repo.findAll().stream().map(assembler::toResource)
                                           .collect(Collectors.toList());
        return new Resources<>(all, linkTo(methodOn(FavoriteController.class).favorites()).withSelfRel());
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @GetMapping("favorites/{id}")
    Resource<FavoriteItem> findFavorite(@PathVariable Integer id) {
        FavoriteItem favorite = repo.findById(id).orElseThrow(() -> new FavoriteNotFoundException(id));

        return assembler.toResource(favorite);
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @PostMapping("/favorites/{location}")
    Resource<FavoriteItem> addFavorite(@PathVariable String location) {
        return new Resource<FavoriteItem>(repo.save(new FavoriteItem(location)));
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @DeleteMapping("/favorites/{id}")
    void removeFavorite(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
