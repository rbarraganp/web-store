package com.iesruizgijon.fleamarket.controller;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.Filter;
import com.iesruizgijon.fleamarket.model.dto.AdDto;
import com.iesruizgijon.fleamarket.model.dto.AdDtoRest;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/*AD CONTROLLER INTERFACE*/
public interface AdController {

    //Create ad
    public FleaMarketResponse<Void> createAdController(AdDto adDto)throws FleaMarketException;

    //Delete AD by Id
    public FleaMarketResponse<Void> deleteAdController(Integer refid)throws FleaMarketException;


    //Update ad
    public  FleaMarketResponse<Void> upAdController(AdDto adDto)throws FleaMarketException;


    //Return list all ad
    public FleaMarketResponse<List<AdDtoRest>> findAllAdController()throws FleaMarketException;


    //Return list ads from user
    public  FleaMarketResponse<List<AdDtoRest>> findAdFromUserService(Integer idUser)throws FleaMarketException;

    //Return list user´s likes ads
    public FleaMarketResponse<List<AdDtoRest>> findLikeAdFromUserController(Integer idUser)throws FleaMarketException;


    //Return list ad by SubCategory
    public FleaMarketResponse<List<AdDtoRest>> findAdBySubcategoryController(Integer idSubCat)throws FleaMarketException;

    //Return list ad by Category
   public FleaMarketResponse<List<AdDtoRest>> findAdByCategoryController(Integer idCat)throws FleaMarketException;



   //Return list ad by filter

   //public FleaMarketResponse<List<AdDtoRest>> findAdByFilterController(Filter filter)throws FleaMarketException;

   public FleaMarketResponse<List<AdDtoRest>> findAdByFilterController(String search, Integer cat, Integer subcat, Integer ac,
          Integer pr, Integer lo, Double min_price, Double max_price, Boolean status_nuevo, Boolean status_usado, Boolean status_reparado, Boolean status_averiado, String order_price)throws FleaMarketException;



   //Return ad by refAd
    public FleaMarketResponse<AdDtoRest> findAdByRefAdController(Integer refAd)throws FleaMarketException;

}
