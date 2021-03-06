package me.wuts0n.hausrafaelapp.firebase.object;


import android.support.annotation.NonNull;

public class TeamMemberObject implements Comparable {

    private String name;
    private String description;
    private String picture;
    private String phone;
    private String fax;
    private String email;


    public TeamMemberObject() {
    }

    public TeamMemberObject(String name,
                            String description,
                            String picture,
                            String phone,
                            String fax,
                            String email) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("\\\\n", "\n");
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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


    @Override
    public int compareTo(@NonNull Object obj) {
        if (obj instanceof TeamMemberObject) {
            TeamMemberObject otherOne = (TeamMemberObject) obj;
            return otherOne.getImportance() - this.getImportance();
        }
        return 1;
    }

    /*
    *   Einrichtungsleiter > Bezugstherapeut > AT > Fr
    */
    private int getImportance() {
        int importance = 0;
        if (name.startsWith("Fr")) {
            importance += 1 << 0;
        }
        if (description.contains("AT")) {
            importance += 1 << 1;
        }
        if (description.contains("Bezugstherapeut")) {
            importance += 1 << 2;
        }
        if (description.contains("Einrichtungsleiter")) {
            importance += 1 << 3;
        }
        return importance;
    }
}
