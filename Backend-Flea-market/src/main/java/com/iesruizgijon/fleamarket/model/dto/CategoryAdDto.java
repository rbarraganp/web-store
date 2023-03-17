package com.iesruizgijon.fleamarket.model.dto;

import java.util.List;

public class CategoryAdDto {


    private Integer idCAt;

    private String nameCat;

    private List<SubCategoryAdDto> subCatList;


    public CategoryAdDto() {}

    public CategoryAdDto(Integer idCAt, String nameCat) {
        this.idCAt = idCAt;
        this.nameCat = nameCat;
    }

    public CategoryAdDto(Integer idCAt, String nameCat, List<SubCategoryAdDto> subCatList) {
        this.idCAt = idCAt;
        this.nameCat = nameCat;
        this.subCatList = subCatList;
    }

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

    public List<SubCategoryAdDto> getSubCatList() { return subCatList; }

    public void setSubCatList(List<SubCategoryAdDto> subCatList) { this.subCatList = subCatList;}
}
