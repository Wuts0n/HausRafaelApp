package me.wuts0n.hausrafaelapp.firebase.object;


public class ContactObject {

    private String picture;
    private String description;
    private String location;
    private String phone;
    private String fax;
    private String email;
    private String website;


    public ContactObject() {
    }

    public ContactObject(String picture,
                         String description,
                         String location,
                         String phone,
                         String fax,
                         String email,
                         String website) {
        this.picture = picture;
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
