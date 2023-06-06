package com.iesruizgijon.fleamarket.security.service;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ACDto;


import java.util.List;

/**
 * AC SERVICE INTERFACE
 */

public interface ACService {


    //Create AC
    public void createACService(ACDto ACDto)throws FleaMarketException;


    //Delete AC by idAC
    public void deleteACService(Integer idAC)throws FleaMarketException;


    //Return List All AC
    public List<ACDto> findAllAC() throws FleaMarketException;




}
