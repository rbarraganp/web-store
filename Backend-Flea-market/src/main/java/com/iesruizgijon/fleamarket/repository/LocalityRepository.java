package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.Locality;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Localty REPOSITORY
 */
public interface LocalityRepository extends JpaRepository<Locality, Integer> {
   List<Locality> findByPrId_IdPr(Integer idPr);





}
