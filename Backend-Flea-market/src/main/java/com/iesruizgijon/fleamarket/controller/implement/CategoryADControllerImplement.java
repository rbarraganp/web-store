package com.iesruizgijon.fleamarket.controller.implement;

import com.iesruizgijon.fleamarket.controller.CategoryAdController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.CategoryAdDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.CategoryAdService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * CATEGORY CONTROLLER IMPLEMENT
 */
@CrossOrigin
@RestController
@RequestMapping(RestConstants.RESOURCE_CAT)
public class CategoryADControllerImplement implements CategoryAdController {


    @Autowired
    CategoryAdService categoryAdService;


    /**
     * Create new category
     * @param categoryAdDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> createCategoryAdController(
            @ApiParam(value = RestConstants.PARAMETER_CATEGORY)
            @RequestBody CategoryAdDto categoryAdDto) throws FleaMarketException {

        categoryAdService.createCategoryAdService(categoryAdDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }



    /**
     * Delete category by idCAt
     * @param idCat
     * @return
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_CAT_ID)
    public FleaMarketResponse<Void> deleteCategoryAdController(
            @ApiParam(value = RestConstants.PARAMETER_AC_ID)
            @PathVariable Integer idCat) throws FleaMarketException {

        categoryAdService.deleteCategoryAdService(idCat);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }


    /**
     * Return list all categories
     * @return FleaMarketResponse<List<CategoryAdDto>>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_CAT_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<CategoryAdDto>>findAllCategoryController() throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                categoryAdService.findAllCategoryAd());

    }




    /**
     * Return list all categories and subcategories
     * @return FleaMarketResponse<List<CategoryAdDto>>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_CAT_LIST_AND_SUBCAT, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<CategoryAdDto>>findAllCategoryAndSubCategoriesController() throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                categoryAdService.findAllCategoryAdAndSubcategories());

    }



}
