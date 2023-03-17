package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.SubCategoryAdDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import java.util.List;

/**
 * SUB-CATEGORY_AD CONTROLLER INTERFACE
 */
public interface SubCategoryAdController {

    //Create SubCategoryAd
    public FleaMarketResponse<Void> createSubCategoryController(SubCategoryAdDto subCategoryAdDto)throws FleaMarketException;


    //Delete SubCategoryAd by idSubCAt
    public FleaMarketResponse<Void> deleteSubCategoryController(Integer idSubCAt)throws FleaMarketException;


    //Return List SuCategory by idCAt
    public FleaMarketResponse<List<SubCategoryAdDto>>  findByCategoryController(Integer idCAt) throws FleaMarketException;
}
