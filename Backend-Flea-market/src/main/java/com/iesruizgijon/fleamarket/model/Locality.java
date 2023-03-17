package com.iesruizgijon.fleamarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(value = { "adList" })
@Entity
@Table(name = "Locality")
public class Locality {


    @ManyToOne
    @JoinColumn(name = "pr_id", nullable = false)
    private Province prId;

    @Id
    @Column(name="id_lo")
    private Integer idLo;

    @Column(name="name_lo", nullable = false)
    private String nameLo;


    //RELATION

    //Table Ad
  //  @OneToMany(fetch = FetchType.LAZY,  mappedBy = "idLo")
    //private List<Ad> adList;


    //BUILDER

    public Locality() {}

    public Locality(Integer idLo) {
        this.idLo = idLo;
    }

    public Locality(Province prId, Integer idLo, String nameLo) {
        this.prId = prId;
        this.idLo = idLo;
        this.nameLo = nameLo;
    }






    //GET AND SET

    public Province getPrId() {
        return prId;
    }

    public void setPrId(Province prId) {
        this.prId = prId;
    }


    public Integer getIdLo() {
        return idLo;
    }

    public void setIdLo(Integer idLo) {
        this.idLo = idLo;
    }

    public String getNameLo() {
        return nameLo;
    }

    public void setNameLo(String nameLo) {
        this.nameLo = nameLo;
    }

    /*
    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

     */
}
