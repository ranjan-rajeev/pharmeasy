package com.pharmeasy.model;

public class UserData {
    /**
     * id : 1
     * first_name : George
     * last_name : Bluth
     * avatar : https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg
     */

    private int id;
    private String first_name;
    private String last_name;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}