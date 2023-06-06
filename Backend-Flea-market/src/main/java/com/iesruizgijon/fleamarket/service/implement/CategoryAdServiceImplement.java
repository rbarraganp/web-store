package com.iesruizgijon.fleamarket.security.service.implement;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.CategoryAd;
import com.iesruizgijon.fleamarket.model.dto.CategoryAdDto;
import com.iesruizgijon.fleamarket.repository.CategoryAdRepository;
import com.iesruizgijon.fleamarket.security.service.CategoryAdService;
import com.iesruizgijon.fleamarket.security.service.SubCategoryAdService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CATEGORY_AD SERVICE IMPLEMENT
 */

@Service
public class CategoryAdServiceImplement implements CategoryAdService {


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CategoryAdRepository categoryAdRepo;

    @Autowired
    SubCategoryAdService subCatService;

    /**
     * Create new CategoryAd
     * @param categoryAdDto
     * @throws FleaMarketException
     */
    @Override
    public void createCategoryAdService(CategoryAdDto categoryAdDto) throws FleaMarketException {

        CategoryAd categoryAd = modelMapper.map(categoryAdDto, CategoryAd.class);

        categoryAdRepo.save(categoryAd);

    }

    /**
     * Delete Category by idCAt
     * @param idCAt
     * @throws FleaMarketException
     */
    @Override
    public void deleteCategoryAdService(Integer idCAt) throws FleaMarketException {

        CategoryAd categoryAd =  categoryAdRepo.findById(idCAt)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CATEGORY));

        categoryAdRepo.delete(categoryAd);

    }

    /**
     * Return list all Categories
     * @return List<CategoryAdDto>
     * @throws FleaMarketException
     */
    @Override
    public List<CategoryAdDto> findAllCategoryAd() throws FleaMarketException {

        return   categoryAdRepo.findAll().stream()
                .map(cat->modelMapper.map(cat, CategoryAdDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Return list all Categories and subcategories
     * @return List<CategoryAdDto>
     * @throws FleaMarketException
     */

    @Override
    public List<CategoryAdDto> findAllCategoryAdAndSubcategories() throws FleaMarketException {

        List<CategoryAdDto> categoryAdDtoList=   categoryAdRepo.findAll().stream()
                .map(cat->modelMapper.map(cat, CategoryAdDto.class))
                .collect(Collectors.toList());


        for (CategoryAdDto cat: categoryAdDtoList) {

            cat.setSubCatList(subCatService.findByCategory(cat.getIdCAt()));

        }

        return categoryAdDtoList;


    }

}
