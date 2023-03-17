package com.iesruizgijon.fleamarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(value = { " provinceList" })
@Entity
@Table(name = "AC")
public class AC {

    //ATTRIBUTES
    @Id
    @Column(name="id_ac")
    private Integer idAc;

    @Column(name="name_ac", nullable = false, unique = true)
    private String nameAc;



    //RELATION

    //Table Province
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "acId", fetch = FetchType.LAZY)
    //private List<Province> provinceList;


    //BUILDER

    public AC(Integer idAc, String nameAc) {
        this.idAc = idAc;
        this.nameAc = nameAc;
    }

    public AC() {

    }


    //GET AND SET

    public Integer getIdAc() {
        return idAc;
    }

    public void setIdAc(Integer idAc) {
        this.idAc = idAc;
    }

    public String getNameAc() {
        return nameAc;
    }

    public void setNameAc(String nameAc) {
        this.nameAc = nameAc;
    }

    /*
    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

     */
}
