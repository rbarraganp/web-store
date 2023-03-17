package com.iesruizgijon.fleamarket.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iesruizgijon.fleamarket.model.Province;

@JsonIgnoreProperties("prId")
public class LocalityDto {


    private Province prId;

    private Integer idLo;

    private String nameLo;

    public LocalityDto() {}

    public LocalityDto(Province prId, Integer idLo, String nameLo) {
        this.prId = prId;
        this.idLo = idLo;
        this.nameLo = nameLo;
    }

    public Province getPrId() { return prId; }

    public void setPrId(Province prId) { this.prId = prId; }

    public Integer getIdLo() { return idLo; }

    public void setIdLo(Integer idLo) { this.idLo = idLo; }

    public String getNameLo() { return nameLo; }

    public void setNameLo(String nameLo) { this.nameLo = nameLo; }
}
