package com.iesruizgijon.fleamarket.controller.implement;

import com.iesruizgijon.fleamarket.controller.UserController;
import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.model.dto.UserDto;
import com.iesruizgijon.fleamarket.model.dto.UserDtoRest;
import com.iesruizgijon.fleamarket.reponses.FleaMarketResponse;
import com.iesruizgijon.fleamarket.security.service.UserService;
import com.iesruizgijon.fleamarket.util.constans.CommonConstants;
import com.iesruizgijon.fleamarket.util.constans.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/*USER CONTROLLER IMPLEMENT*/
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_USER)
@RestController
public class UserControllerImp implements UserController {

    @Autowired
    UserService userService;



    /**
     * Registry new User
     * @param newUserDto
     * @return
     * @throws FleaMarketException
     */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstants.RESOURCE_USER_CHECK, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> checkInNewUser(
            @ApiParam(value = RestConstants.PARAMETER_USER, required = true)
            @RequestBody UserDto newUserDto)throws FleaMarketException{

        userService.createUser(newUserDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.OK);
    }




    /**
     * Delete User by idUser
     * @param idUser
     * @return
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(RestConstants.RESOURCE_USER_ID)
    public FleaMarketResponse<Void> deleteUser(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID)
           @PathVariable Integer idUser) throws FleaMarketException {

        userService.deleteUserById(idUser);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT), CommonConstants.OK);
    }




    /**
     * Update user by json UserDto
     * @param updateUserDto
     * @return
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> updateUser(
            @ApiParam(value = RestConstants.PARAMETER_USER, required = true)
           @RequestBody UserDto updateUserDto) throws FleaMarketException {

        userService.updateUser(updateUserDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);

    }





    /**
     * Update password User by json User
     * @param userDto
     * @return
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.RESOURCE_USER_PASSWORD, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<Void> updatePasswordUser(
           @ApiParam(value = RestConstants.PARAMETER_USER)
            @RequestBody UserDto userDto) throws FleaMarketException {

        userService.updatePassUser(userDto);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);

    }



    /**
     * Check Exist Nick User
     * @param nickUser
     * @return  FleaMarketResponse<Boolean>
     * @throws FleaMarketException
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_USER_NICK)
    public FleaMarketResponse<Boolean> checkExistNickUser(
            @ApiParam(value = RestConstants.PARAMETER_USER_NICK)
            @PathVariable String nickUser) throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),
                CommonConstants.OK,userService.checkExistNickUser(nickUser) );

    }



    /**
     * Check Exist Gmail User
     * @param emailUser
     * @return FleaMarketResponse<Boolean>
     * @throws FleaMarketException
     */
   @Override
   @ResponseStatus(HttpStatus.OK)
   @GetMapping(value = RestConstants.RESOURCE_USER_EMAIL)
   public FleaMarketResponse<Boolean> checkExistEmailUser(
           @ApiParam(value = RestConstants.PARAMETER_USER_EMAIL)
           @PathVariable String emailUser) throws FleaMarketException {

       return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),
               CommonConstants.OK,  userService.checkExistEmailUser(emailUser));

   }


    /**
     * Return user by id (all data)
     * @param idUser
     * @return FleaMarketResponse<UserDtoRest>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<UserDtoRest> returnUserById(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID)
            @PathVariable Integer idUser) throws FleaMarketException {


        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                userService.findUserById(idUser));
    }





    /**
     * Return user by ide (short data)
     * @param idUser
     * @return FleaMarketResponse<UserDtoRest>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_USER_SHORT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<UserDtoRest> returnShortUserById(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID)
            @PathVariable Integer idUser) throws FleaMarketException {


        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                userService.findShortUserById(idUser));
    }




    /**
     * Return user by ide (medium data)
     * @param idUser
     * @return FleaMarketResponse<UserDtoRest>
     * @throws FleaMarketException
     */

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_USER_MEDIUM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<UserDtoRest> returnMediumUserById(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID)
            @PathVariable Integer idUser) throws FleaMarketException {


        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                userService.findMediumUserById(idUser));
    }




    /**
     * Return list UserDto
     * @return FleaMarketResponse<List<UserDtoRest>>
     * @throws FleaMarketException
     */
 /*   @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping (value = RestConstants.RESOURCE_USER_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FleaMarketResponse<List<UserDtoRest>> returnAllUser() throws FleaMarketException {

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK),CommonConstants.OK,
                userService.findAll());
    }
*/


    /**
     * Add like ad
     * @param idUser
     * @param refAd
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @PostMapping(value = RestConstants.RESOURCE_USER_LIKE)
    public FleaMarketResponse<Void> addLikedAd(
          @ApiParam(value = RestConstants.PARAMETER_USER_ID, required = true) @PathVariable Integer idUser,
          @ApiParam(value = RestConstants.PARAMETER_AD_REF, required = true) @PathVariable Integer refAd)
            throws FleaMarketException {

        userService.addLikedAd(idUser, refAd);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED),CommonConstants.OK);
    }


    /**
     * Delete like ad
     * @param idUser
     * @param refAd
     * @return FleaMarketResponse<Void>
     * @throws FleaMarketException
     */
    @Override
    @DeleteMapping(value = RestConstants.RESOURCE_USER_LIKE)
    public FleaMarketResponse<Void> deleteLikedAd(
            @ApiParam(value = RestConstants.PARAMETER_USER_ID, required = true) @PathVariable Integer idUser,
            @ApiParam(value = RestConstants.PARAMETER_AD_REF, required = true) @PathVariable Integer refAd)
            throws FleaMarketException {

        userService.deleteLikedAd(idUser, refAd);

        return new FleaMarketResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.NO_CONTENT),CommonConstants.OK);
    }





}
