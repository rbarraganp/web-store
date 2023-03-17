package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * USER REPOSITORY
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {



    //Check if nickUser exist
    Optional<User> findByNickUser(String nickUser);

    //Check if email exists
    Optional<User> findByEmailUser(String emailUser);

    //Return user by idUser and passwordUser
    Optional<User> findByIdUserAndPasswordUser(Integer idUser, String passwordUser);

    //Return user by emailUser and passwordUser
    Optional<User> findByEmailUserAndPasswordUser(String emailUser, String passwordUser);










}
