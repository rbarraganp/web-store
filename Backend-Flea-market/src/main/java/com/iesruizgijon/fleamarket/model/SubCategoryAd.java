package com.iesruizgijon.fleamarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(value = { "adList" })
@Entity
@Table(name = "SubCategoryAd")
public class SubCategoryAd {

    //ATTRIBUTES
    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private  CategoryAd catId;

    @Id
    @Column(name = "id_subCat")
    private  Integer idSubCat;

    @Column(name = "name_subCat")
    private String nameSubCat;


    public SubCategoryAd() {}

    public SubCategoryAd(Integer idSubCat) {
        this.idSubCat = idSubCat;
    }

    public SubCategoryAd(CategoryAd catId, Integer idSubCat, String nameSubCat) {
        this.catId = catId;
        this.idSubCat = idSubCat;
        this.nameSubCat = nameSubCat;
    }


    //GET AND SET
    public CategoryAd getCatId() {
        return catId;
    }

    public void setCatId(CategoryAd catId) {
        this.catId = catId;
    }

    public Integer getIdSubCat() {return idSubCat; }

    public void setIdSubCat(Integer idSubCat) { this.idSubCat = idSubCat;}

    public String getNameSubCat() {
        return nameSubCat;
    }

    public void setNameSubCat(String nameSubCat) {
        this.nameSubCat = nameSubCat;
    }

}
