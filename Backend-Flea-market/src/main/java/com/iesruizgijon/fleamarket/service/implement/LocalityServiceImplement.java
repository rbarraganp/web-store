package com.iesruizgijon.fleamarket.security.service.implement;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.Locality;
import com.iesruizgijon.fleamarket.model.dto.LocalityDto;
import com.iesruizgijon.fleamarket.repository.LocalityRepository;
import com.iesruizgijon.fleamarket.security.service.LocalityService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * LOCALITY SERVICE IMPLEMENT
 */

@Service
public class LocalityServiceImplement implements LocalityService{


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    LocalityRepository localityRepo;


    /**
     * Create new Locality
     * @param localityDto
     * @throws FleaMarketException
     */
    @Override
    public void createLocalityService(LocalityDto localityDto) throws FleaMarketException {

        Locality locality = modelMapper.map(localityDto, Locality.class);

        localityRepo.save(locality);

    }


    /**
     * Delete Locality by idLo
     * @param idLo
     * @throws FleaMarketException
     */
    @Override
    public void deleteLocalityService(Integer idLo) throws FleaMarketException {

        Locality locality= localityRepo.findById(idLo)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_LOCALITY));

        localityRepo.delete(locality);

    }




    /**
     * Return list locality by idPro
     * @param idPr
     * @return List<LocalityDto>
     * @throws FleaMarketException
     */
    @Override
    public List<LocalityDto> findByPr(Integer idPr) throws FleaMarketException {

        return   localityRepo.findByPrId_IdPr(idPr).stream()
                .map(lo->modelMapper.map(lo, LocalityDto.class))
                .collect(Collectors.toList());
    }

}
