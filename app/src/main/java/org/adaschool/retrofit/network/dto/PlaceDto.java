package org.adaschool.retrofit.network.dto;

import java.io.Serializable;

public class PlaceDto implements Serializable {

    private String name;
    private String price;
    private String address;
    private String phone;
    private String schedule;
    private String restrictions;

    private String description;
    private String imagen;

    public PlaceDto() {
    }

    public PlaceDto(String name, String price, String address, String phone, String schedule, String restrictions, String description, String imagen) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.phone = phone;
        this.schedule = schedule;
        this.restrictions = restrictions;
        this.imagen = imagen;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public void update(PlaceDto place){
        this.address = place.getAddress();
        this.phone = place.getPhone();
        this.price = place.getPrice();
        this.schedule = place.getSchedule();
        this.restrictions = place.getRestrictions();
        this.imagen = place.getImagen();
        this.description = place.getDescription();
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}