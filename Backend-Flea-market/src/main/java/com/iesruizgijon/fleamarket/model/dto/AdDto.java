package com.iesruizgijon.fleamarket.model.dto;


import com.iesruizgijon.fleamarket.model.User;
import com.iesruizgijon.fleamarket.model.emun.ItemStatus;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class AdDto {



        //ATTRIBUTES

        private Integer refAd;
        private Integer userId;
        private ItemStatus itemStatusAd;
        private Timestamp dateAd;
        private Timestamp lastUpdateAd;
        private Integer subCatId;
        private String titleAd;
        private String descriptionAd;
        private Double priceAd;
        private Integer idLo;




        //RELATION

        //Table Like ( Reference to User)
        private Set<User> likeUserList;

        //Table Photo
        private List<PhotoDto> photoList;



        //BUILDEr

        public AdDto() {}


    public AdDto(Integer userId, ItemStatus itemStatusAd, Integer subCatId, String titleAd,
                 String descriptionAd, Double priceAd, Integer idLo, List<PhotoDto> photoList) {
        this.userId = userId;
        this.itemStatusAd = itemStatusAd;
        this.subCatId = subCatId;
        this.titleAd = titleAd;
        this.descriptionAd = descriptionAd;
        this.priceAd = priceAd;
        this.idLo = idLo;
        this.photoList = photoList;
    }




    public AdDto(ItemStatus itemStatusAd, Integer refAd, Integer subCatId,
                 String titleAd, String descriptionAd, Double priceAd, Integer idLo,
                 List<PhotoDto> photoList) {
        this.refAd = refAd;
        this.itemStatusAd = itemStatusAd;
        this.subCatId = subCatId;
        this.titleAd = titleAd;
        this.descriptionAd = descriptionAd;
        this.priceAd = priceAd;
        this.idLo = idLo;
        this.photoList = photoList;
    }



    public AdDto(Integer refAd, Integer userId, ItemStatus itemStatusAd, Timestamp dateAd,
                 Timestamp lastUpdateAd, Integer subCatId, String titleAd, String descriptionAd,
                 Double priceAd, Integer idLo, Set<User> likeUserList, List<PhotoDto> photoList) {
            this.refAd = refAd;
            this.userId = userId;
            this.itemStatusAd = itemStatusAd;
            this.dateAd = dateAd;
            this.lastUpdateAd = lastUpdateAd;
            this.subCatId = subCatId;
            this.titleAd = titleAd;
            this.descriptionAd = descriptionAd;
            this.priceAd = priceAd;
            this.idLo = idLo;
            this.likeUserList = likeUserList;
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

    public Timestamp getDateAd() {
        return dateAd;
    }

    public void setDateAd(Timestamp dateAd) {
        this.dateAd = dateAd;
    }

    public Timestamp getLastUpdateAd() {
        return lastUpdateAd;
    }

    public void setLastUpdateAd(Timestamp lastUpdateAd) {
        this.lastUpdateAd = lastUpdateAd;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
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

    public Integer getIdLo() {
        return idLo;
    }

    public void setIdLo(Integer idLo) {
        this.idLo = idLo;
    }

    public Set<User> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(Set<User> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public List<PhotoDto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoDto> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return "AdDto{" +
                "refAdDto=" + refAd +
                ", userIdDto=" + userId +
                ", itemStatusAdDto=" + itemStatusAd +
                ", dateAdDto=" + dateAd +
                ", lastUpdateAdDto=" + lastUpdateAd +
                ", subCatIdDto=" + subCatId +
                ", tittleAdDto='" + titleAd + '\'' +
                ", descriptionAdDto='" + descriptionAd + '\'' +
                ", priceAdDto=" + priceAd +
                ", idLoDto=" + idLo +
                ", likeUserListDto=" + likeUserList +
                ", photoListDto=" + photoList +
                '}';
    }
}
