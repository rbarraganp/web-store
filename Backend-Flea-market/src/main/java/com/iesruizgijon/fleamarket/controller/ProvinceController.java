package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import java.util.List;

/**
 * PROVINCE CONTROLLER INTERFACE
 */
public interface ProvinceController {

    //Create Province
    public FleaMarketResponse<Void> createNewProvinceController(ProvinceDto provinceDto)throws FleaMarketException;


    //Delete Province by idAC
    public FleaMarketResponse<Void> deleteProvinceController(Integer idPr)throws FleaMarketException;


    //Return List All Province By idAC
    public FleaMarketResponse<List<ProvinceDto>> findAllACController(Integer idAC) throws FleaMarketException;
}
