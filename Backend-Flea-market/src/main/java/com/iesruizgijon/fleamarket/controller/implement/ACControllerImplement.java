package com.iesruizgijon.fleamarket.controller.implement;

import com.iesruizgijon.fleamarket.controller.ACController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.ACDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.ACService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * AC CONTROLLER IMPLEMENT
 */

@CrossOrigin
@RestController
@RequestMapping(RestConstants.RESOURCE_AC)
public class ACControllerImplement implements ACController {


    @Autowired
    ACService acService;


    /**
     * Create new AC
     * @param acDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> createACController(
            @ApiParam(value = RestConstants.PARAMETER_AC)
            @RequestBody ACDto acDto) throws FleaMarketException {

        acService.createACService(acDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }


    /**
     * Delete Ac by idAC
     * @param idAC
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_AC_Id)
    public FleaMarketResponse<Void> deleteACController(
            @ApiParam(value = RestConstants.PARAMETER_AC_ID)
            @PathVariable Integer idAC) throws FleaMarketException {

        acService.deleteACService(idAC);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }


    /**
     * Return list all Ac
     * @return  FleaMarketResponse<List<ACDto>>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_AC_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<ACDto>> findAllACController() throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                acService.findAllAC());

    }
}
