package com.iesruizgijon.fleamarket.model.dto;

public class PhotoDto {


    //Attribute

    private Integer codPhoto;

   // private Ad adRefDto;

    private String urlPhoto;


    //Builder
    public PhotoDto() {}

    public PhotoDto( String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public PhotoDto(Integer codPhoto, String urlPhoto) {
        this.codPhoto = codPhoto;
        this.urlPhoto = urlPhoto;
    }


    //Get And Set

    public Integer getCodPhoto() { return codPhoto;}

    public void setCodPhoto(Integer codPhoto) { this.codPhoto = codPhoto; }

    public String getUrlPhoto() { return urlPhoto; }

    public void setUrlPhoto(String urlPhoto) { this.urlPhoto = urlPhoto; }
}
