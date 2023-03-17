package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.Ad;
import com.iesruizgijon.fleamarket.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PHOTO REPOSITORY
 */
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> deleteByAdRef(Ad adRef);


}
