package com.iesruizgijon.fleamarket.model.dto;

public class PhotoDtoRest {


    //Attribute
    private Integer codPhoto;

    private String urlPhoto;


    //Builder
    public PhotoDtoRest() {}

    public PhotoDtoRest(Integer codPhoto, String urlPhoto) {
        this.codPhoto = codPhoto;
        this.urlPhoto = urlPhoto;
    }


    //Get and Set


    public Integer getCodPhoto() {return codPhoto; }

    public void setCodPhoto(Integer codPhoto) { this.codPhoto = codPhoto; }

    public String getUrlPhoto() { return urlPhoto; }

    public void setUrlPhoto(String urlPhoto) { this.urlPhoto = urlPhoto; }

}
