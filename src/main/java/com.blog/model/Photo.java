package com.blog.model;

/**
 * Created by Administrator on 2018/8/3.
 */
public class Photo {

    private Integer id;

    private String photoName;

    private String photoFileByte;

    private String photoPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoFileByte() {
        return photoFileByte;
    }

    public void setPhotoFileByte(String photoFileByte) {
        this.photoFileByte = photoFileByte;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
