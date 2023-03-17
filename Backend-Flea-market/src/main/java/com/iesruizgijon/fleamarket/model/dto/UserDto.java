package com.iesruizgijon.fleamarket.model.dto;

import com.iesruizgijon.fleamarket.model.Ad;
import com.iesruizgijon.fleamarket.model.emun.RoleUser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserDto {


    //ATTRIBUTES

    private Integer idUser;
    private String nickUser;
    private String emailUser;
    private String nameUser;
    private String passwordUser;
    private RoleUser roleUser = RoleUser.user;
    private Timestamp dischargeDateUser;
    private Timestamp lastAccDateUser;
    private Integer phoneUser;
    private Boolean showNameUser = false;
    private Boolean showEmailUser = true;
    private Boolean showPhoneUser = true;

    private String newPasswordUser;



    //RELATION

    //Table Ad
    private List<Ad> adListDto= new ArrayList<>();

    //Table Like ( Reference to AD)
    private Set<Ad> likeAdListDto=new HashSet<>();




    //BUILDER

    public UserDto(){};

    //CREATE NEW USER
    public UserDto(String nickUser, String emailUser, String passwordUser, String nameUser, Integer phoneUser) {
        this.nickUser = nickUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
    }

    //UPDATE USER
    public UserDto(Integer idUser, String nickUser, String nameUser, Integer phoneUser,
                   Boolean showNameUser, Boolean showEmailUser, Boolean showPhoneUser) {
        this.idUser = idUser;
        this.nickUser = nickUser;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.showNameUser = showNameUser;
        this.showEmailUser = showEmailUser;
        this.showPhoneUser = showPhoneUser;
    }

    //UPDATE PASSWORD USER

    public UserDto(Integer idUser, String passwordUser, String newPasswordUser) {
        this.idUser = idUser;
        this.passwordUser = passwordUser;
        this.newPasswordUser = newPasswordUser;
    }


    //ADD/DELETE LIKED AD TO LIST LIKEADLIST
    public UserDto(Integer idUser, Set<Ad> likeAdListDto) {
        this.idUser = idUser;
        this.likeAdListDto = likeAdListDto;
    }

    //ADD/DELETE  AD TO LIST ADLIST


    public UserDto(Integer idUser, String nickUser, String emailUser, String nameUser,
                   String passwordUser, RoleUser roleUser, Timestamp dischargeDateUser,
                   Timestamp lastAccDateUser, Integer phoneUser, Boolean showNameUser,
                   Boolean showEmailUser, Boolean showPhoneUser, List<Ad> adListDto,
                   Set<Ad> likeAdListDto) {
        this.idUser = idUser;
        this.nickUser = nickUser;
        this.emailUser = emailUser;
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
        this.dischargeDateUser = dischargeDateUser;
        this.lastAccDateUser = lastAccDateUser;
        this.phoneUser = phoneUser;
        this.showNameUser = showNameUser;
        this.showEmailUser = showEmailUser;
        this.showPhoneUser = showPhoneUser;
        this.adListDto = adListDto;
        this.likeAdListDto = likeAdListDto;
    }

    //GET AND SET

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNickUser() {
        return nickUser;
    }

    public void setNickUser(String nickUser) {
        this.nickUser = nickUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public Timestamp getDischargeDateUser() {
        return dischargeDateUser;
    }

    public void setDischargeDateUser(Timestamp dischargeDateUser) {this.dischargeDateUser = dischargeDateUser;}

    public Timestamp getLastAccDateUser() {
        return lastAccDateUser;
    }

    public void setLastAccDateUser(Timestamp lastAccDateUser) {
        this.lastAccDateUser = lastAccDateUser;
    }

    public Integer getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(Integer phoneUser) {
        this.phoneUser = phoneUser;
    }

    public Boolean getShowNameUser() {
        return showNameUser;
    }

    public void setShowNameUser(Boolean showNameUser) {
        this.showNameUser = showNameUser;
    }

    public Boolean getShowEmailUser() {return showEmailUser;}

    public void setShowEmailUser(Boolean showEmailUser) {
        this.showEmailUser = showEmailUser;
    }

    public Boolean getShowPhoneUser() { return showPhoneUser;}

    public void setShowPhoneUser(Boolean showPhoneUser) {
        this.showPhoneUser = showPhoneUser;
    }

    public List<Ad> getAdListDto() {
        return adListDto;
    }

    public void setAdListDto(List<Ad> adListDto) {
        this.adListDto = adListDto;
    }

    public Set<Ad> getLikeAdListDto() {
        return likeAdListDto;
    }

    public void setLikeAdListDto(Set<Ad> likeAdListDto) {
        this.likeAdListDto = likeAdListDto;
    }


    public String getNewPasswordUser() { return newPasswordUser; }

    public void setNewPasswordUser(String newPasswordUser) { this.newPasswordUser = newPasswordUser; }
}





