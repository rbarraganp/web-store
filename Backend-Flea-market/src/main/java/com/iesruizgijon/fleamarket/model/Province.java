package com.iesruizgijon.fleamarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(value = { "localityLis" })
@Entity
@Table(name = "Province")
public class Province {

    @ManyToOne
    @JoinColumn(name = "ac_id", nullable = false)
    private AC acId;

    @Id
    @Column(name="id_pr")
    private Integer idPr;

    @Column(name="name_pr", nullable = false)
    private String namePr;


    //RELATION

    //Table Locality
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "prId", fetch = FetchType.EAGER)
    //private List<Locality> localityList;


    //BUILDER

    public Province(AC acId, Integer idPr, String namePr) {
        this.acId = acId;
        this.idPr = idPr;
        this.namePr = namePr;
    }

    public Province() {

    }


    //GET AND SET

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

    /*
    public List<Locality> getLocalityList() {
        return localityList;
    }

    public void setLocalityList(List<Locality> localityList) {
        this.localityList = localityList;
    }
*/
}
