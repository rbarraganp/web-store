package com.iesruizgijon.fleamarket.security.service.implement;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.AC;
import com.iesruizgijon.fleamarket.model.dto.ACDto;
import com.iesruizgijon.fleamarket.repository.ACRepository;
import com.iesruizgijon.fleamarket.security.service.ACService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AC SERVICE IMPLEMENT
 */

@Service
public class ACServiceImplement implements ACService {


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ACRepository acRepo;

    @Override
    public void createACService(ACDto ACDto) throws FleaMarketException {

        AC ac= modelMapper.map(ACDto, AC.class);

        acRepo.save(ac);

    }


    @Override
    public void deleteACService(Integer idAC) throws FleaMarketException {

        AC ac= acRepo.findById(idAC).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_AC));

        acRepo.delete(ac);

    }

    @Override
    public List<ACDto> findAllAC() throws FleaMarketException {

        return   acRepo.findAll().stream()
                .map(ac->modelMapper.map(ac, ACDto.class))
                .collect(Collectors.toList());

    }

}
