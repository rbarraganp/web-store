package com.iesruizgijon.fleamarket.security.service.implement;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.SubCategoryAd;
import com.iesruizgijon.fleamarket.model.dto.SubCategoryAdDto;
import com.iesruizgijon.fleamarket.repository.SubCategoryAdRepository;
import com.iesruizgijon.fleamarket.security.service.SubCategoryAdService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SUB-CATEGORY-AD SERVICE IMPLEMENT
 */

@Service
public class SubCategoryAdServiceImplement implements SubCategoryAdService {


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SubCategoryAdRepository subCategoryAdRepo;

    /**
     * Create new subCategory
     * @param subCategoryAdDto
     * @throws FleaMarketException
     */
    @Override
    public void createSubCategoryAdService(SubCategoryAdDto subCategoryAdDto) throws FleaMarketException {

        SubCategoryAd subCategoryAd = modelMapper.map(subCategoryAdDto, SubCategoryAd.class);

        subCategoryAdRepo.save(subCategoryAd);

    }

    /**
     * Delete Subcategory by idSubCat
     * @param idSubCat
     * @throws FleaMarketException
     */

    @Override
    public void deleteSubCategoryAdService(Integer idSubCat) throws FleaMarketException {

        SubCategoryAd subcategoryAd =  subCategoryAdRepo.findById(idSubCat)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_SUBCATEGORY));

        subCategoryAdRepo.delete(subcategoryAd);

    }

    /**
     * Return list subcategory by idCAt
     * @param idCAt
     * @return List<SubCategoryAdDto>
     * @throws FleaMarketException
     */
    @Override
    public List<SubCategoryAdDto> findByCategory(Integer idCAt ) throws FleaMarketException {

        return   subCategoryAdRepo.findByCatId_IdCAt(idCAt).stream()
                .map(sub->modelMapper.map(sub, SubCategoryAdDto.class))
                .collect(Collectors.toList());
    }

}
