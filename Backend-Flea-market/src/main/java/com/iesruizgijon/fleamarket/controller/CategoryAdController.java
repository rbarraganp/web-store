package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.CategoryAdDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import java.util.List;

/**
 * CATEGORY_AD CONTROLLER INTERFACE
 */
public interface CategoryAdController {

    //Create CategoryAd
    public FleaMarketResponse<Void> createCategoryAdController(CategoryAdDto categoryAdDto)throws FleaMarketException;


    //Delete CategoryAd by idCAt
    public FleaMarketResponse<Void> deleteCategoryAdController(Integer idCAt)throws FleaMarketException;


    //Return List All CategoryAd
    public FleaMarketResponse<List<CategoryAdDto>>  findAllCategoryController() throws FleaMarketException;


    //Return List All CategoryAd and subcategory
    public FleaMarketResponse<List<CategoryAdDto>>  findAllCategoryAndSubCategoriesController() throws FleaMarketException;

}
