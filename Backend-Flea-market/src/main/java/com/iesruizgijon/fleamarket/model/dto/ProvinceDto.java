package com.iesruizgijon.fleamarket.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iesruizgijon.fleamarket.model.AC;


@JsonIgnoreProperties("acId")
public class ProvinceDto {


    private AC acId;

    private Integer idPr;

    private String namePr;


    public ProvinceDto() {}

    public ProvinceDto(AC acId, Integer idPr, String namePr) {
        this.acId = acId;
        this.idPr = idPr;
        this.namePr = namePr;
    }

    public AC getAcId() {
        return acId;
    }

    public void setAcId(AC acId) {
        this.acId = acId;
    }

    public Integer getIdPr() {
        return idPr;
    }

    public void setIdPr(Integer idPr) {
        this.idPr = idPr;
    }

    public String getNamePr() {
        return namePr;
    }

    public void setNamePr(String namePr) {
        this.namePr = namePr;
    }
}
