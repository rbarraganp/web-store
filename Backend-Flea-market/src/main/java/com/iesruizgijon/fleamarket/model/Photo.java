package com.iesruizgijon.fleamarket.model;

import javax.persistence.*;

@Entity
@Table(name = "Photo")
public class Photo {

    //ATTRIBUTE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_photo")
    private Integer codPhoto;


    @ManyToOne
    @JoinColumn(name = "ad_ref", nullable = false)
    private Ad adRef;

    @Column(name = "url_photo", columnDefinition = "LONGTEXT",  nullable = false)
    private String urlPhoto;


    //BUILDER

    public Photo() {}

    public Photo( Ad adRef, String urlPhoto) {
        this.adRef = adRef;
        this.urlPhoto = urlPhoto;
    }



    //GET AND SET

    public Integer getCodPhoto() {
        return codPhoto;
    }

    public void setCodPhoto(Integer codPhoto) {
        this.codPhoto = codPhoto;
    }

    public Ad getAdRef() {
        return adRef;
    }

    public void setAdRef(Ad adRef) {
        this.adRef = adRef;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}

