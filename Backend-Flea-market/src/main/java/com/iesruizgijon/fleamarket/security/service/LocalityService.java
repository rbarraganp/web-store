package com.iesruizgijon.fleamarket.security.service;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.LocalityDto;
import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;

import java.util.List;

/**
 * Locality SERVICE REPOSITORY
 */

public interface LocalityService {


    //Create Locality
    public void createLocalityService(LocalityDto localityDto)throws FleaMarketException;


    //Delete Locality by idLo
    public void deleteLocalityService(Integer idLo)throws FleaMarketException;


    //Return List Locality by idPr
    public List<LocalityDto> findByPr(Integer idPr) throws FleaMarketException;


}
