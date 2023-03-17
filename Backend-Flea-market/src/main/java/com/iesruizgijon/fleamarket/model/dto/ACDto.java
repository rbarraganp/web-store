package com.iesruizgijon.fleamarket.model.dto;

import javax.persistence.Column;

public class ACDto {


    private Integer idAc;

    private String nameAc;


    public ACDto() {}

    public ACDto(Integer idAc, String nameAc) {
        this.idAc = idAc;
        this.nameAc = nameAc;
    }

    public Integer getIdAc() { return idAc; }

    public void setIdAc(Integer idAc) { this.idAc = idAc; }

    public String getNameAc() { return nameAc; }

    public void setNameAc(String nameAc) { this.nameAc = nameAc; }
}
