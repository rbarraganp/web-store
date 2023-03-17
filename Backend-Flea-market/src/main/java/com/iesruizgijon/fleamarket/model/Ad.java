package com.iesruizgijon.fleamarket.model;

import com.iesruizgijon.fleamarket.model.emun.ItemStatus;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Ad")
public class Ad {

    //ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_ad")
    private Integer refAd;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "item_status_ad", nullable = false)
    private ItemStatus itemStatusAd;

    @Column(name = "date_ad", nullable = false)
    @CreationTimestamp
    private Timestamp dateAd;

    @Column(name="last_update_ad", nullable = false)
    @UpdateTimestamp
    private Timestamp lastUpdateAd;

    @ManyToOne
    @JoinColumn(name = "subCat_id", nullable = false)
    private SubCategoryAd subCatId;

    @Column(name = "title_ad", length = 50, nullable = false)
    private String titleAd;

    @Column(name = "description_ad", columnDefinition = "Text" ,length = 2000, nullable = false)
    private String descriptionAd;

    @Column(name = "price_ad", nullable = false)
    @Digits(integer = 7, fraction = 3)
    private Double priceAd;

    @ManyToOne
    @JoinColumn(name ="id_lo", nullable = false )
    private Locality idLo;

  //RELATION


    //Table Like ( Reference to User)
/*    @ManyToMany(fetch = FetchType.LAZY, mappedBy ="likeAdList")
    private Set<User> likeUserList;*/

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable( name = "Likes",
            joinColumns = @JoinColumn(name = "ad_ref"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likeUserList=new HashSet<>();


    //Table Photo
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adRef", fetch = FetchType.EAGER)
    private List<Photo> photoList;




    //BUILDEr

    public Ad() {}

    public Ad(User userId, ItemStatus itemStatusAd, SubCategoryAd subCatId, String titleAd, String descriptionAd,
              Double priceAd, Locality idLo) {
        this.userId = userId;
        this.itemStatusAd = itemStatusAd;
        this.subCatId = subCatId;
        this.titleAd = titleAd;
        this.descriptionAd = descriptionAd;
        this.priceAd = priceAd;
        this.idLo = idLo;
    }

    public Ad(Integer refAd, User userId, ItemStatus itemStatusAd, Timestamp dateAd, Timestamp lastUpdateAd, SubCategoryAd subCadId,
              String titleAd, String descriptionAd, Double priceAd, Locality idLo, Set<User> likeUserList,
              List<Photo> photoList) {
        this.refAd = refAd;
        this.userId = userId;
        this.itemStatusAd = itemStatusAd;
        this.dateAd = dateAd;
        this.subCatId = subCadId;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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

    public SubCategoryAd getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(SubCategoryAd subCatId) {
        this.subCatId = subCatId;
    }

    public String getTitleAd() {
        return titleAd;
    }

    public void setTitleAd(String tittleAd) {
        this.titleAd = tittleAd;
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

    public Locality getIdLo() {
        return idLo;
    }

    public void setIdLo(Locality idLo) {
        this.idLo = idLo;
    }

    public Timestamp getLastUpdateAd() { return lastUpdateAd;}

    public void setLastUpdateAd(Timestamp lastUpdateAd) { this.lastUpdateAd = lastUpdateAd;}

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }


    public Set<User> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(Set<User> likeUserList) {
        this.likeUserList = likeUserList;
    }


    @Override
    public String toString() {
        return "Ad{" +
                "refAd=" + refAd +
                ", userId=" + userId +
                ", itemStatusAd=" + itemStatusAd +
                ", dateAd=" + dateAd +
                ", lastUpdateAd=" + lastUpdateAd +
                ", subCatId=" + subCatId +
                ", tittleAd='" + titleAd + '\'' +
                ", descriptionAd='" + descriptionAd + '\'' +
                ", priceAd=" + priceAd +
                ", idLo=" + idLo +
                ", likeUserList=" + likeUserList +
                ", photoList=" + photoList +
                '}';
    }
}
