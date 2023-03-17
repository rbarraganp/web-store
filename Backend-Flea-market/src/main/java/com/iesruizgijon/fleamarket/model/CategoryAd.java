package com.iesruizgijon.fleamarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@JsonIgnoreProperties(value = { "subCategoryAdList" })
@Entity
@Table(name = "CategoryAd")
public class CategoryAd {

    //ATTRIBUTES

    @Id
    @Column(name = "id_cat")
    private Integer idCAt;

    @Column(name = "name_cat", nullable = false)
    private String nameCat;


    //RELATION

    //Table SubcategoryAD
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "catId", fetch = FetchType.EAGER)
    //private List<SubCategoryAd> subCategoryAdList;


    //BUILDER
    public CategoryAd() {}

    /*
    public CategoryAd(Integer idCAt, String nameCat, List<SubCategoryAd> subCategoryAdList) {
        this.idCAt = idCAt;
        this.nameCat = nameCat;
        this.subCategoryAdList = subCategoryAdList;
    }
*/

    public CategoryAd(Integer idCAt, String nameCat) {
        this.idCAt = idCAt;
        this.nameCat = nameCat;
    }

    //GET AND SET

    public Integer getIdCAt() {
        return idCAt;
    }

    public void setIdCAt(Integer idCAt) {
        this.idCAt = idCAt;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }


}
