package com.iesruizgijon.fleamarket.controller.implement;

import com.iesruizgijon.fleamarket.controller.AdController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.Filter;
import com.iesruizgijon.fleamarket.model.dto.AdDto;
import com.iesruizgijon.fleamarket.model.dto.AdDtoRest;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.AdService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*AD CONTROLLER IMPLEMENT*/
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_AD)
@RestController
public class AdControllerImplement implements AdController {


    @Autowired
    AdService adService;



    /**
     * Create ad by AdDto
     * @param adDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstants.RESOURCE_AD_NEW, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> createAdController(
            @ApiParam(value = RestConstants.PARAMETER_AD, required = true)
            @RequestBody AdDto adDto) throws FleaMarketException {

        adService.createAdService(adDto);

       return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.OK);
    }





    /**
     * Delete AD by refAd
     * @param refAd
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_AD_REF)
    public FleaMarketResponse<Void> deleteAdController(
            @ApiParam(value = RestConstants.PARAMETER_AD_REF, required = true)
            @PathVariable Integer refAd) throws FleaMarketException {

        adService.deleteAdService(refAd);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);

    }





    /**
     * Update ad
     * @param adDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> upAdController(
            @ApiParam(value = RestConstants.PARAMETER_AD, required = true)
            @RequestBody AdDto adDto) throws FleaMarketException {

        adService.upAdService(adDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
    }





    /**
     * List all ad
     * @return  FleaMarketResponse<List<AdDtoRest>>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_ALL ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findAllAdController() throws FleaMarketException {


        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAllAdService());
    }






    /**
     * Return list user´s ads
     * @param idUser
     * @return  FleaMarketResponse<List<AdDtoRest>>
     * @throws FleaMarketException
     */
    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_USER ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findAdFromUserService(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID, required = true)
            @PathVariable Integer idUser)  throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAdFromUserService(idUser));

    }




    /**
     * Return list like ad´s from user
     * @param idUser
     * @return FleaMarketResponse<List<AdDtoRest>>
     * @throws FleaMarketException
     */
    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_LIKE_USER ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findLikeAdFromUserController(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID, required = true)
            @PathVariable Integer idUser)  throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findLikeAdFromUserService(idUser));
    }




    /**
     * Return list ad by SubCategory
     * @param idSubCat
     * @return  FleaMarketResponse<List<AdDtoRest>>
     * @throws FleaMarketException
     */
    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_SUB_CAT ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findAdBySubcategoryController(
            @ApiParam(value = RestConstants.PARAMETER_SUBCATEGORY_ID, required = true)
            @PathVariable Integer idSubCat)  throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAdBySubcategoryService(idSubCat));
    }



    /**
     * Return list ad by Category
     * @param idCate
     * @return FleaMarketResponse<List<AdDtoRest>>
     * @throws FleaMarketException
     */
    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_CAT ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findAdByCategoryController(
            @ApiParam(value = RestConstants.PARAMETER_CATEGORY_ID, required = true)
            @PathVariable Integer idCate)  throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAdByCategoryService(idCate));
    }


    /**
     *
     * @param search
     * @param cat
     * @param subcat
     * @param ac
     * @param pr
     * @param lo
     * @param min_price
     * @param max_price
     * @param status_nuevo
     * @param status_usado
     * @param status_reparado
     * @param status_averiado
     * @param order_price
     * @return
     * @throws FleaMarketException
     */

    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_LIST_FILTER , produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<AdDtoRest>> findAdByFilterController(

            @ApiParam(value = RestConstants.PARAMETER_FILTER_SEARCH, required = true) @RequestParam String search,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_CAT, required = true) @RequestParam(defaultValue = "-1")  Integer cat,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_SUBCAT, required = true) @RequestParam(defaultValue = "-1")  Integer subcat,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_AC, required = true) @RequestParam(defaultValue = "-1")  Integer ac,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_PR, required = true) @RequestParam(defaultValue = "-1")  Integer pr,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_LO, required = true) @RequestParam(defaultValue = "-1")  Integer lo,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_MIN_PRICE, required = true) @RequestParam(defaultValue = "-1")  Double min_price,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_MAX_PRICE, required = true) @RequestParam(defaultValue = "-1")   Double max_price,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_STATUS_NUEVO, required = true) @RequestParam  Boolean status_nuevo,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_STATUS_USADO, required = true) @RequestParam Boolean status_usado,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_STATUS_REPARADO, required = true) @RequestParam Boolean status_reparado,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_STATUS_AVERIADO, required = true) @RequestParam Boolean status_averiado,
            @ApiParam(value = RestConstants.PARAMETER_FILTER_ORDER_PRICE, required = true) @RequestParam String order_price)

            throws FleaMarketException {

        Filter filter= new Filter();
        filter.setSearch(search);
        filter.setCat(cat);
        filter.setSubcat(subcat);
        filter.setAc(ac);
        filter.setPr(pr);
        filter.setLo(lo);
        filter.setMin_price(min_price);
        filter.setMax_price(max_price);
        filter.setStatus_nuevo(status_nuevo);
        filter.setStatus_usado(status_usado);
        filter.setStatus_reparado(status_reparado);
        filter.setStatus_averiado(status_averiado);
        filter.setOrder_price(order_price);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAdByFilterService(filter));

    }


    /**
     * Return ad by refAd
     * @param refAd
     * @return FleaMarketResponse<AdDtoRest>
     * @throws FleaMarketException
     */

    @Override
    @GetMapping(value = RestConstants.RESOURCE_AD_REF , produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<AdDtoRest> findAdByRefAdController(
            @ApiParam(value = RestConstants.PARAMETER_AD_REF, required = true)
            @PathVariable Integer refAd) throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                adService.findAdByRefAdService(refAd));

    }
}
