package com.iesruizgijon.fleamarket.security.service;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ACDto;
import com.iesruizgijon.fleamarket.model.dto.CategoryAdDto;
import com.iesruizgijon.fleamarket.model.dto.SubCategoryAdDto;

import java.util.List;

/**
 * CATEGORY_AD SERVICE INTERFACE
 */

public interface CategoryAdService {


    //Create CategoryAd
    public void createCategoryAdService(CategoryAdDto categoryAdDto)throws FleaMarketException;


    //Delete CategoryAd by idCat
    public void deleteCategoryAdService(Integer idCAt)throws FleaMarketException;


    //Return List CategoryAd
    public List<CategoryAdDto> findAllCategoryAd() throws FleaMarketException;



    //Return List SubCategoryAd by Category and Subcategories
    public List<CategoryAdDto> findAllCategoryAdAndSubcategories() throws FleaMarketException;
}
