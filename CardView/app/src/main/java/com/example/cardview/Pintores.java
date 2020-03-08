package com.example.cardview;

public class Pintores {
    String nombre;
    String desc;
    int photoId;

    public Pintores(String name, String desc, int photoId) {
        this.nombre = name;
        this.desc = desc;
        this.photoId = photoId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getDesc() {
        return desc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
