package com.iesruizgijon.fleamarket.controller.implement;

import com.iesruizgijon.fleamarket.controller.SubCategoryAdController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.SubCategoryAdDto;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.SubCategoryAdService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * SUB-CATEGORY-AD CONTROLLER IMPLEMENT
 */
@CrossOrigin
@RestController
@RequestMapping(RestConstants.RESOURCE_SUB_CAT)
public class SubCategoryADControllerImplement implements SubCategoryAdController {


    @Autowired
    SubCategoryAdService subCategoryAdService;


    /**
     * Create new SubCategory
     * @param subCategoryAdDto
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> createSubCategoryController(
            @ApiParam(value = RestConstants.PARAMETER_SUBCATEGORY)
            @RequestBody SubCategoryAdDto subCategoryAdDto) throws FleaMarketException {

        subCategoryAdService.createSubCategoryAdService(subCategoryAdDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }


    /**
     * Delete subcategory by idSubCat
     * @param idSubCat
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_SUB_CAT_ID)
    public FleaMarketResponse<Void> deleteSubCategoryController(
            @ApiParam(value = RestConstants.PARAMETER_SUBCATEGORY_ID)
            @PathVariable Integer idSubCat) throws FleaMarketException {

        subCategoryAdService.deleteSubCategoryAdService(idSubCat);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }


    /**
     * Return list subcategories by idCAt
     * @param idCat
     * @return FleaMarketResponse<List<SubCategoryAdDto>>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_SUB_CAT_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<SubCategoryAdDto>> findByCategoryController(
            @ApiParam(value = RestConstants.PARAMETER_CATEGORY_ID)
            @PathVariable Integer idCat) throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                subCategoryAdService.findByCategory(idCat));

    }

}
