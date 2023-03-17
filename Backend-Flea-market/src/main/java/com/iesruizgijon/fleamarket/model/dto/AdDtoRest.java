package com.iesruizgijon.fleamarket.model.dto;

import com.iesruizgijon.fleamarket.model.emun.ItemStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class AdDtoRest {



    //ATTRIBUTES

    private Integer refAd;
    private Integer userId;
    private ItemStatus itemStatusAd;
    private Timestamp lastUpdateAd;
    private String subCatId;
    private String titleAd;
    private String descriptionAd;
    private Double priceAd;
    private String idLo;
    private Integer numLikes;



    //RELATION

    //Table Like ( Reference to User)
    //private Set<User> likeUserListDtoReturn;

    //Table Photo
    private List<PhotoDtoRest> photoList;


    //Builder


    public AdDtoRest() {
    }


    public AdDtoRest(Integer refAd, Integer userId, ItemStatus itemStatusAd, Timestamp
            lastUpdateAd, String subCatId, String titleAd,
                     String descriptionAd, Double priceAd, String idLo, Integer numLikes,
                     List<PhotoDtoRest> photoList) {
        this.refAd = refAd;
        this.userId = userId;
        this.itemStatusAd = itemStatusAd;
        this.lastUpdateAd = lastUpdateAd;
        this.subCatId = subCatId;
        this.titleAd = titleAd;
        this.descriptionAd = descriptionAd;
        this.priceAd = priceAd;
        this.idLo = idLo;
        this.numLikes= numLikes;
        this.photoList = photoList;
    }




    //GET AND SET


    public Integer getRefAd() {
        return refAd;
    }

    public void setRefAd(Integer refAd) {
        this.refAd = refAd;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ItemStatus getItemStatusAd() {
        return itemStatusAd;
    }

    public void setItemStatusAd(ItemStatus itemStatusAd) {
        this.itemStatusAd = itemStatusAd;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Timestamp getLastUpdateAd() {
        return lastUpdateAd;
    }

    public void setLastUpdateAd(Timestamp lastUpdateAd) {
        this.lastUpdateAd = lastUpdateAd;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }



    public String getTitleAd() {
        return titleAd;
    }

    public void setTitleAd(String titleAd) {
        this.titleAd = titleAd;
    }

    public String getDescriptionAd() {
        return descriptionAd;
    }

    public void setDescriptionAd(String descriptionAd) {
        this.descriptionAd = descriptionAd;
    }

    public Double getPriceAd() {
        return priceAd;
    }

    public void setPriceAd(Double priceAd) {
        this.priceAd = priceAd;
    }

    public String getIdLo() {
        return idLo;
    }

    public void setIdLo(String idLo) {
        this.idLo = idLo;
    }


    public List<PhotoDtoRest> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoDtoRest> photoList) {
        this.photoList = photoList;
    }
}
