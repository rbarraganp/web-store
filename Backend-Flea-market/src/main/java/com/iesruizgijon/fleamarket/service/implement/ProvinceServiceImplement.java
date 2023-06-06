package com.iesruizgijon.fleamarket.security.service.implement;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.Province;
import com.iesruizgijon.fleamarket.model.dto.ProvinceDto;
import com.iesruizgijon.fleamarket.repository.ProvinceRepository;
import com.iesruizgijon.fleamarket.security.service.ProvinceService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PROVINCE SERVICE IMPLEMENT
 */

@Service
public class ProvinceServiceImplement implements ProvinceService {


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ProvinceRepository provinceRepo;

    /**
     * Create new Province
     * @param provinceDto
     * @throws FleaMarketException
     */
    @Override
    public void createProvinceService(ProvinceDto provinceDto) throws FleaMarketException {

        Province province= modelMapper.map(provinceDto, Province.class);

        provinceRepo.save(province);

    }


    /**
     *
     * @param idPr
     * @throws FleaMarketException
     */
    @Override
    public void deleteProvinceService(Integer idPr) throws FleaMarketException {

        Province province= provinceRepo.findById(idPr).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_PROVINCE));

        provinceRepo.delete(province);

    }


    /**
     *
     * @param idAC
     * @return List<ProvinceDto>
     * @throws FleaMarketException
     */
    @Override
    public List<ProvinceDto> findByAC(Integer idAC) throws FleaMarketException {

        return   provinceRepo.findByAcId_IdAc(idAC).stream()
                .map(pro->modelMapper.map(pro, ProvinceDto.class))
                .collect(Collectors.toList());
    }

}
