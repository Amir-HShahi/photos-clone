package com.example.photos.clone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PHOTOS")
public class PhotoModel {

    @Id // mark for database
    private Integer id;

    @NotEmpty // import validation dependency
    // there are others to check in External libraries
    private String fileName;

    @JsonIgnore
    private String contentType;

    //raw data
    @JsonIgnore //from jackson library, won't send this field to front end
    private byte[] data;

    public PhotoModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
