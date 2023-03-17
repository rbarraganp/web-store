package com.iesruizgijon.fleamarket.controller.implement;


import com.iesruizgijon.fleamarket.controller.ProvinceController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;

import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;

import com.iesruizgijon.fleamarket.security.service.ProvinceService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * PROVINCE CONTROLLER IMPLEMENT
 */
@CrossOrigin
@RestController
@RequestMapping(RestConstants.RESOURCE_PROVINCE)
public class ProvinceControllerImplement implements ProvinceController {


    @Autowired
    ProvinceService provinceService;


    /**
     * Create new Province
     * @param provinceDto
     * @return  FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void>createNewProvinceController(
            @ApiParam(value = RestConstants.PARAMETER_PR)
            @RequestBody ProvinceDto provinceDto) throws FleaMarketException {

        provinceService.createProvinceService(provinceDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }


    /**
     * Delete province by idPr
     * @param idPr
     * @return
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_PROVINCE_ID)
    public FleaMarketResponse<Void> deleteProvinceController(
            @ApiParam(value = RestConstants.PARAMETER_PR_ID)
            @PathVariable Integer idPr) throws FleaMarketException {

        provinceService.deleteProvinceService(idPr);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }


    /**
     * Return list Localities by idPr
     * @param idAC
     * @return FleaMarketResponse<List<ProvinceDto>>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_PROVINCE_LIST_BY_AC, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<ProvinceDto>> findAllACController(
            @ApiParam(value = RestConstants.PARAMETER_AC_ID)
            @PathVariable Integer idAC) throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                provinceService.findByAC(idAC));

    }
}
