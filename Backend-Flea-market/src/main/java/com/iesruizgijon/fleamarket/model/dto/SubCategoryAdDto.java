package com.iesruizgijon.fleamarket.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iesruizgijon.fleamarket.model.CategoryAd;


@JsonIgnoreProperties("catId")
public class SubCategoryAdDto {

    private CategoryAd catId;

    private  Integer idSubCat;

    private String nameSubCat;


    public SubCategoryAdDto() {}

    public SubCategoryAdDto(CategoryAd catId, Integer idSubCat, String nameSubCat) {
        this.catId = catId;
        this.idSubCat = idSubCat;
        this.nameSubCat = nameSubCat;
    }

    public CategoryAd getCatId() { return catId; }

    public void setCatId(CategoryAd catId) { this.catId = catId; }

    public Integer getIdSubCat() { return idSubCat; }

    public void setIdSubCat(Integer idSubCat) { this.idSubCat = idSubCat; }

    public String getNameSubCat() { return nameSubCat; }

    public void setNameSubCat(String nameSubCat) { this.nameSubCat = nameSubCat; }
}
