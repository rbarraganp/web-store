package com.iesruizgijon.fleamarket.security.service;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;

import com.iesruizgijon.fleamarket.model.dto.SubCategoryAdDto;

import java.util.List;

/**
 * SUB-CATEGORY-AD SERVICE INTERFACE
 */

public interface SubCategoryAdService {


    //Create SubCategoryAd
    public void createSubCategoryAdService(SubCategoryAdDto subCategoryAdDto)throws FleaMarketException;


    //Delete SubCategoryAd by idSubCat
    public void deleteSubCategoryAdService(Integer idSubCAt)throws FleaMarketException;


    //Return List SubCategoryAd by Category
    public List<SubCategoryAdDto> findByCategory(Integer idCAt) throws FleaMarketException;




    

}
