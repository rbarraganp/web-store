package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.LocalityDto;
import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import java.util.List;

/**
 * LOCALITY CONTROLLER INTERFACE
 */
public interface LocalityController {

    //Create new  Locality
    public FleaMarketResponse<Void> createNewLocalityController(LocalityDto LocalityDto)throws FleaMarketException;


    //Delete Locality by idLo
    public FleaMarketResponse<Void> deleteLocalityController(Integer idLo)throws FleaMarketException;


    //Return List Locality By idPr
    public FleaMarketResponse<List<LocalityDto>> findByProvinceController(Integer idAC) throws FleaMarketException;
}
