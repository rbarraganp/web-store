package com.iesruizgijon.fleamarket.controller.implement;


import com.iesruizgijon.fleamarket.controller.LocalityController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.LocalityDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.LocalityService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * LOCALITY CONTROLLER IMPLEMENT
 */
@CrossOrigin
@RestController
@RequestMapping(RestConstants.RESOURCE_LOCALITY)
public class LocalityControllerImplement implements LocalityController {


    @Autowired
    LocalityService localityService;


    /**
     * Create new locality
     * @param localityDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void>createNewLocalityController(
            @ApiParam(value = RestConstants.PARAMETER_LOC)
            @RequestBody LocalityDto localityDto) throws FleaMarketException {

        localityService.createLocalityService(localityDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }


    /**
     * Delete Locality by idLo
     * @param idLo
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_LOCALITY_ID)
    public FleaMarketResponse<Void> deleteLocalityController(
            @ApiParam(value = RestConstants.PARAMETER_LOC_ID)
            @PathVariable Integer idLo) throws FleaMarketException {

        localityService.deleteLocalityService(idLo);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }


    /**
     * Return list localities by idPr
     * @param idPr
     * @return
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_LOCALITY_LIST_BY_PR, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<LocalityDto>> findByProvinceController(
            @ApiParam(value = RestConstants.PARAMETER_PR_ID)
            @PathVariable Integer idPr) throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                localityService.findByPr(idPr));

    }
}
