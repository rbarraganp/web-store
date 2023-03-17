package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ACDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import java.util.List;

/**
 * AC CONTROLLER INTERFACE
 */

public interface ACController {

    //Create AC
    public FleaMarketResponse<Void> createACController(ACDto ACDto)throws FleaMarketException;


    //Delete AC by idAC
    public FleaMarketResponse<Void> deleteACController(Integer idAC)throws FleaMarketException;


    //Return List All AC
    public FleaMarketResponse<List<ACDto>> findAllACController() throws FleaMarketException;
}
