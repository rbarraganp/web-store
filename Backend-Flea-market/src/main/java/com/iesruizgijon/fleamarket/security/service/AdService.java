package com.iesruizgijon.fleamarket.security.service;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.Filter;
import com.iesruizgijon.fleamarket.model.dto.AdDto;
import com.iesruizgijon.fleamarket.model.dto.AdDtoRest;
import org.springframework.http.ResponseEntity;

import java.util.List;


/*AD SERVICE INTERFACE*/
public interface AdService {


    //Create ad
    public void createAdService(AdDto adDto)throws FleaMarketException;

    //Delete ad by refAd
    public void deleteAdService(Integer refAd)throws FleaMarketException;

    //Update ad
    public void upAdService(AdDto adDto)throws FleaMarketException;

    //Return list all ad
    public List<AdDtoRest>  findAllAdService() throws FleaMarketException;

    //Return list ad´s from user
    public List<AdDtoRest> findAdFromUserService(Integer idUser) throws FleaMarketException;


    //Return list like ad´s from user
    public List<AdDtoRest> findLikeAdFromUserService(Integer idUser)throws FleaMarketException;

    //Return list ad by SubCategory
    public List<AdDtoRest> findAdBySubcategoryService(Integer idSubCat)throws FleaMarketException;

    //Return list ad by Category
    public List<AdDtoRest> findAdByCategoryService(Integer idCat)throws FleaMarketException;

    //Return list ad by filter
    public List<AdDtoRest> findAdByFilterService(Filter filter)throws FleaMarketException;

    //Return ad by refAd
    public AdDtoRest findAdByRefAdService(Integer refAd)throws FleaMarketException;

}
