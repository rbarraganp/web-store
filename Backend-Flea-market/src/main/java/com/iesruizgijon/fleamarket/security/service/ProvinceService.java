package com.iesruizgijon.fleamarket.security.service;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ACDto;
import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;

import java.util.List;

/**
 * PROVINCE SERVICE REPOSITORY
 */

public interface ProvinceService {


    //Create Province
    public void createProvinceService(ProvinceDto provinceDto)throws FleaMarketException;


    //Delete Province by idPr
    public void deleteProvinceService(Integer idPro)throws FleaMarketException;


    //Return List Province by idAC
    public List<ProvinceDto> findByAC(Integer idAC) throws FleaMarketException;




}
