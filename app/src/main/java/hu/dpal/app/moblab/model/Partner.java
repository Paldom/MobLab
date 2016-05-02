package hu.dpal.app.moblab.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by dpal on 17/04/16.
 */
@Table
public class Partner extends SugarRecord {

    private Long id;
    private String createdAt;
    private String updatedAt;
    private String img;
    private String title;
    private String address;
    private String desc;
    private String website;
    private String phone;
    private String[] coordinates;

    @Override
    public boolean equals(Object o) {
        Partner p = (Partner) o;
        return p.getId().equals(this.getId()) &&
                p.getCreatedAt().equals(this.getCreatedAt()) &&
                p.getUpdatedAt().equals(this.getUpdatedAt()) &&
                p.getImg().equals(this.getImg()) &&
                p.getTitle().equals(this.getTitle()) &&
                p.getAddress().equals(this.getAddress()) &&
                p.getDesc().equals(this.getDesc()) &&
                p.getWebsite().equals(this.getWebsite()) &&
                p.getPhone().equals(this.getPhone());
    }

    public Partner() {
    }

    public Partner(String img,
                   String title,
                   String desc,
                   String address,
                   String website,
                   String phone,
                   String[] coordinates) {
        this.img = img;
        this.title = title;
        this.desc = desc;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.coordinates = coordinates;

        this.createdAt = new Date().toString();
        this.updatedAt = new Date().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

}
