package com.favorite.service;

import org.springframework.data.jpa.repository.JpaRepository;

interface FavoriteRepository extends JpaRepository<FavoriteItem, Integer> {}
