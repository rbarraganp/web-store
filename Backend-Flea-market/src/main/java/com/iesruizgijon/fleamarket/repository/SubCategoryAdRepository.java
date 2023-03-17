package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.CategoryAd;
import com.iesruizgijon.fleamarket.model.SubCategoryAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * SUB-CATEGORY-AD REPOSITORY
 */
public interface SubCategoryAdRepository extends JpaRepository<SubCategoryAd, Integer> {
    List<SubCategoryAd> findByCatId_IdCAt(Integer idCAt);


}
