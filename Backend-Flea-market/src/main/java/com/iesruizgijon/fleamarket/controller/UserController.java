package com.iesruizgijon.fleamarket.controller;

import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.UserDto;
import com.iesruizgijon.fleamarket.model.dto.UserDtoRest;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/*USER CONTROLLER INTERFACE*/
public interface UserController {

    //Registry new User
    public FleaMarketResponse<Void> checkInNewUser(@RequestBody UserDto newUserDto)throws FleaMarketException;


    //Delete User
    public FleaMarketResponse<Void>  deleteUser(Integer idUser) throws FleaMarketException;

    //Update User
    public FleaMarketResponse<Void>  updateUser(@RequestBody  UserDto updateUserDto)throws FleaMarketException;;


    //Update password User
    public   FleaMarketResponse<Void> updatePasswordUser(@RequestBody UserDto userDto )throws FleaMarketException;


    //Check Exist Nick User
    public FleaMarketResponse<Boolean> checkExistNickUser( String nickUser)throws FleaMarketException;



    //Check Exist Gmail User
    public FleaMarketResponse<Boolean>  checkExistEmailUser(String emailUser)throws FleaMarketException;


    //Return user by idUser
    public FleaMarketResponse<UserDtoRest> returnUserById(Integer idUser) throws FleaMarketException;

    //Return short user by idUser
    public FleaMarketResponse<UserDtoRest> returnShortUserById(Integer idUser) throws FleaMarketException;


    //Return short user by idUser
    public FleaMarketResponse<UserDtoRest> returnMediumUserById(Integer idUser) throws FleaMarketException;


    //Return list all user
    //public FleaMarketResponse<List<UserDtoRest>>returnAllUser()throws FleaMarketException;


    //Add like ad
   public FleaMarketResponse<Void>  addLikedAd(Integer idUser, Integer refAd)throws FleaMarketException;


    //delete like ad
    public FleaMarketResponse<Void>  deleteLikedAd(Integer idUser, Integer refAd)throws FleaMarketException;
}
