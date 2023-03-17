package com.iesruizgijon.fleamarket.model.dto;

import com.iesruizgijon.fleamarket.model.Ad;
import com.iesruizgijon.fleamarket.model.emun.RoleUser;


import java.sql.Timestamp;
import java.util.Set;


public class UserDtoRest {


    //ATTRIBUTES

    private Integer idUser;
    private String nickUser;
    private String emailUser;
    private String nameUser;

    private Timestamp dischargeDateUser;
    private RoleUser roleUser = RoleUser.user;
    private Integer phoneUser;
    private Boolean showNameUser = false;
    private Boolean showEmailUser = true;
    private Boolean showPhoneUser = true;

    private Set<Ad> likeAdList;


    //BUILDER

    public UserDtoRest(){};


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

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
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

    public Boolean getShowEmailUser() {
        return showEmailUser;
    }

    public void setShowEmailUser(Boolean showEmailUser) {
        this.showEmailUser = showEmailUser;
    }

    public Boolean getShowPhoneUser() {
        return showPhoneUser;
    }

    public void setShowPhoneUser(Boolean showPhoneUser) {
        this.showPhoneUser = showPhoneUser;
    }

    public Timestamp getDischargeDateUser() { return dischargeDateUser; }

    public void setDischargeDateUser(Timestamp dischargeDateUser) { this.dischargeDateUser = dischargeDateUser; }

    public Set<Ad> getLikeAdList() {
        return likeAdList;
    }

    public void setLikeAdList(Set<Ad> likeAdList) {
        this.likeAdList = likeAdList;
    }
}





