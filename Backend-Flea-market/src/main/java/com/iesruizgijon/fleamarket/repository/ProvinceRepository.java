package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Province REPOSITORY
 */
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByAcId_IdAc(Integer idAc);

}
