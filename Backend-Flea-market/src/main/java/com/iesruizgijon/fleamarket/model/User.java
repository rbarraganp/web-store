package com.iesruizgijon.fleamarket.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iesruizgijon.fleamarket.model.emun.RoleUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import  java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = { "adList", "likeAdList" })
@Table(name = "User")
public class User {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Integer idUser;

    @Column(name="nick_user", length = 20,  unique = true, nullable = false)
    private String nickUser;

    @Column(name="email_user", unique = true, nullable = false)
    private String  emailUser;

    @Column(name = "name_user", length = 50, nullable = false)
    private String nameUser;

    @Column(name="password_User", nullable = false)
    private String passwordUser;
    @Column(name = "role_User", nullable = false)
    private RoleUser roleUser= RoleUser.user;

    @Column(name ="discharge_date_user", nullable = false)
    @CreationTimestamp
    private Timestamp dischargeDateUser;

    @Column(name="last_acc_date_user", nullable = false)
    @UpdateTimestamp
    private Timestamp lastAccDateUser;

    @Column(name = "phone_user", length = 9, nullable = false)
    private Integer phoneUser;

    @Column(name = "show_name_user", nullable = false)
    private Boolean showNameUser= false;

    @Column(name = "show_email_user", nullable = false)
    private Boolean showEmailUser= true;

    @Column(name = "show_phone_user", nullable = false)
     private Boolean showPhoneUser= true;



    //RELATION

    //Table Ad
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Ad> adList= new ArrayList<>();


    //Table Like ( Reference to AD)
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable( name = "Likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ad_ref"))
    private Set<Ad> likeAdList=new HashSet<>();




    //BUILDER

    public User(){};

    //CREATE NEW USER
    public User(String nickUser, String emailUser, String passwordUser, String nameUser, Integer phoneUser) {
        this.nickUser = nickUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
    }

    //UPDATE USER
    public User(Integer idUser, String nickUser, String nameUser, Integer phoneUser,
                Boolean showNameUser, Boolean showEmailUser, Boolean showPhoneUser) {
        this.idUser = idUser;
        this.nickUser = nickUser;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.showNameUser = showNameUser;
        this.showEmailUser = showEmailUser;
        this.showPhoneUser = showPhoneUser;
    }

    public User(Integer idUser, String nickUser, String emailUser, String nameUser, String passwordUser,
                RoleUser roleUser, Timestamp dischargeDateUser, Timestamp lastAccDateUser, Integer phoneUser,
                Boolean showNameUser, Boolean showEmailUser, Boolean showPhoneUser, List<Ad> adList, Set likeAdList)
    {
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
        this.adList = adList;
        this.likeAdList = likeAdList;
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

    public void setDischargeDateUser(Timestamp dischargeDateUser) {
        this.dischargeDateUser = dischargeDateUser;
    }

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

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public Set<Ad> getLikeAdList() {
        return likeAdList;
    }

    public void setLikeAdList(Set<Ad> likeAdList) {
        this.likeAdList = likeAdList;
    }
}
