package com.iesruizgijon.fleamarket.security.service.implement;


import com.iesruizgijon.fleamarket.exceptions.*;
import com.iesruizgijon.fleamarket.model.Ad;
import com.iesruizgijon.fleamarket.model.User;
import com.iesruizgijon.fleamarket.model.dto.UserDto;
import com.iesruizgijon.fleamarket.model.dto.UserDtoRest;
import com.iesruizgijon.fleamarket.repository.AdRepository;
import com.iesruizgijon.fleamarket.repository.UserRepository;
import com.iesruizgijon.fleamarket.security.service.UserService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AdRepository adRepo;

    private ModelMapper modelMapper = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplement.class);

   /* Create new User*/
    public void createUser(UserDto newUserDto)throws FleaMarketException {

        try {

                if(userRepo.findByEmailUser(newUserDto.getEmailUser()).isPresent() ||
                    userRepo.findByNickUser(newUserDto.getNickUser()).isPresent()) {

                    throw new AlreadyReportedExecption(ExceptionConstants.MESSAGE_ALREADY_EXIST_USER);
                }

        } catch (final Exception e) {

            LOGGER.error(ExceptionConstants.MESSAGE_ALREADY_EXIST_USER, e);
            throw new AlreadyReportedExecption(ExceptionConstants.MESSAGE_ALREADY_EXIST_USER);
        }

        /*Codificar password user*/
        newUserDto.setPasswordUser(new BCryptPasswordEncoder().encode(newUserDto.getPasswordUser()));

        User user = modelMapper.map(newUserDto, User.class);
        userRepo.save(user);

    }



    /*Delete User by idUser */
    @Override
    public void deleteUserById(Integer idUser)throws FleaMarketException {

     User user= userRepo.findById(idUser).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

     userRepo.delete(user);

    }


    /*Update user by json UserDto*/
    @Override
    public void updateUser(UserDto updateUserDto)throws FleaMarketException {

        User user = userRepo.findById(updateUserDto.getIdUser())
                .orElseThrow(() -> new NoContentExeption(ExceptionConstants.MESSAGE_INEXISTENT_USER));

            user.setNickUser(updateUserDto.getNickUser());
            user.setNameUser(updateUserDto.getNameUser());
            user.setPhoneUser(updateUserDto.getPhoneUser());
            user.setShowNameUser(updateUserDto.getShowNameUser());
            user.setShowEmailUser(updateUserDto.getShowEmailUser());
            user.setShowPhoneUser(updateUserDto.getShowPhoneUser());


            userRepo.save(user);

    }


    /*Update password User by json User*/
    @Override
    public void updatePassUser(UserDto userDto)throws FleaMarketException {


        User user =  userRepo.findById(userDto.getIdUser())
                .orElseThrow(() -> new NoContentExeption(ExceptionConstants.MESSAGE_USER_PASSWORD_FAIL));

        /*Comparamos la contraseña encriptada con la recibida*/
        if( new BCryptPasswordEncoder().matches(userDto.getPasswordUser(), user.getPasswordUser())){

            /*Codificar nueva contraseña user*/
            userDto.setNewPasswordUser(new BCryptPasswordEncoder().encode(userDto.getNewPasswordUser()));

            user.setPasswordUser(userDto.getNewPasswordUser());

            userRepo.save(user);

        }else{

             throw new  NoContentExeption(ExceptionConstants.MESSAGE_USER_PASSWORD_FAIL);
        }
    }



    /*Check Exist Nick User*/
    @Override
    public boolean checkExistNickUser(String nickUser)throws FleaMarketException {

        return  (userRepo.findByNickUser(nickUser).isPresent());
    }


    /*Check Exist Gmail User*/
    @Override
    public boolean checkExistEmailUser(String emailUser) throws FleaMarketException {

        return  (userRepo.findByEmailUser(emailUser).isPresent());

    }


   /* Return user by ide*/
    @Override
    public UserDtoRest findUserById(Integer idUser)throws FleaMarketException {


        return userRepo.findById(idUser)
                .map(user->modelMapper.map(user, UserDtoRest.class))
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

    }


    @Override
    public UserDtoRest findShortUserById(Integer idUser)throws FleaMarketException {


        User user= userRepo.findById(idUser)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

        UserDtoRest userDtoRest= new UserDtoRest();

            userDtoRest.setIdUser(user.getIdUser());
            userDtoRest.setNickUser(user.getNickUser());
            userDtoRest.setDischargeDateUser(user.getDischargeDateUser());

        if(user.getShowNameUser()){ userDtoRest.setNameUser(user.getNameUser());}
        if(user.getShowEmailUser()){ userDtoRest.setEmailUser(user.getEmailUser());}
        if(user.getShowPhoneUser()){ userDtoRest.setPhoneUser(user.getPhoneUser());}

        return userDtoRest;


    }


    @Override
    public UserDtoRest findMediumUserById(Integer idUser)throws FleaMarketException {


        User user= userRepo.findById(idUser)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

        UserDtoRest userDtoRest= new UserDtoRest();

        userDtoRest.setIdUser(user.getIdUser());
        userDtoRest.setNickUser(user.getNickUser());
        userDtoRest.setEmailUser(user.getEmailUser());
        userDtoRest.setDischargeDateUser(user.getDischargeDateUser());
        userDtoRest.setNameUser(user.getNameUser());
        userDtoRest.setPhoneUser(user.getPhoneUser());
        userDtoRest.setShowNameUser(user.getShowNameUser());
        userDtoRest.setShowEmailUser(user.getShowEmailUser());
        userDtoRest.setShowPhoneUser(user.getShowPhoneUser());

        return userDtoRest;


    }



    /*Return list UserDto */
    @Override
    public List<UserDtoRest> findAll()throws FleaMarketException{

        return   userRepo.findAll().stream()
                .map(user->modelMapper.map(user,UserDtoRest.class))
                .collect(Collectors.toList());


    }




    /*Delete like ad*/
    @Override
    public  void addLikedAd(Integer idUser, Integer refAd)throws FleaMarketException{


       User user= userRepo.findById(idUser)
               .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

       Ad ad= adRepo.findById(refAd)
               .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_AD));

       Set<Ad> likeList= user.getLikeAdList();

       likeList.add(ad);

       user.setLikeAdList(likeList);

       userRepo.save(user);

    }



    @Override
    public void deleteLikedAd(Integer idUser, Integer refAd) throws FleaMarketException {


        User user= userRepo.findById(idUser)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

        Ad ad= adRepo.findById(refAd)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_AD));

        Set<Ad> likeList= user.getLikeAdList();

        likeList.remove(ad);

        user.setLikeAdList(likeList);

        userRepo.save(user);

    }


    //Return user By email and Password
    public UserDtoRest loginUser(String emailUser, String passwordUser)throws FleaMarketException{


        return userRepo.findByEmailUserAndPasswordUser(emailUser,passwordUser)
                .map(user -> modelMapper.map(user,UserDtoRest.class))
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

    }








    //********CONVERT ******************************************************

    //UserDto to User Converter (Builder Create)


    public User createNewUser(UserDto userDto){
        User user= new User();

        user.setNickUser(userDto.getNickUser());
        user.setEmailUser(userDto.getEmailUser());
        user.setPasswordUser(userDto.getPasswordUser());
        user.setNameUser(userDto.getNameUser());
        user.setPhoneUser(userDto.getPhoneUser());

    return user;
    }

    /*

    //User to UserDtoRest Converter
    public UserDtoRest createNewUserDtoRest(User user){
        UserDtoRest userDtoRest = new UserDtoRest();

        userDtoRest.setNickUser(user.getNickUser());
        userDtoRest.setEmailUser(user.getEmailUser());
        userDtoRest.setNameUser(user.getNameUser());
        userDtoRest.setIdUser(user.getIdUser());
        userDtoRest.setPhoneUser(user.getPhoneUser());
        userDtoRest.setShowNameUser(user.getShowNameUser());
        userDtoRest.setShowEmailUser(user.getShowEmailUser());
        userDtoRest.setShowPhoneUser(user.getShowPhoneUser());
        userDtoRest.setRoleUser(user.getRoleUser());
        userDtoRest.setLikeAdList(user.getLikeAdList());

        return userDtoRest;
    }


*/













}
