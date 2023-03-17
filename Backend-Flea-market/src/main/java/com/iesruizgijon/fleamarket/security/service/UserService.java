package com.iesruizgijon.fleamarket.security.service;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.UserDto;
import com.iesruizgijon.fleamarket.model.dto.UserDtoRest;

import java.util.List;

public interface UserService {


    //Create user
    public void createUser(UserDto newUserDto) throws FleaMarketException;

    //Delete user by id
    public void deleteUserById(Integer idUser) throws FleaMarketException;

    //Update user
    public void updateUser(UserDto updateUser) throws FleaMarketException;

    //Update password User
    public void updatePassUser(UserDto userDto) throws FleaMarketException;

    /*Check Exist Nick User*/
    public boolean checkExistNickUser(String nickUser) throws FleaMarketException;

    //Check if email exists
    public boolean checkExistEmailUser(String emailUser)throws FleaMarketException;

    //Return UserDto by Id
    public UserDtoRest findUserById(Integer idUser)throws FleaMarketException;

    //Return short UserDto  by Id
    public UserDtoRest findShortUserById(Integer idUser)throws FleaMarketException;

    //Return medium UserDto  by Id
    public UserDtoRest findMediumUserById(Integer idUser)throws FleaMarketException;

    //Return list userDto
    public List<UserDtoRest> findAll()throws FleaMarketException;

    //Add like ad
    public void addLikedAd(Integer idUser, Integer refAd)throws FleaMarketException;

    //Add like ad
    public void deleteLikedAd(Integer idUser, Integer refAd) throws FleaMarketException;

    //Return user By email and Password
    public UserDtoRest loginUser(String emailUser, String passwordUser)throws FleaMarketException;




}
