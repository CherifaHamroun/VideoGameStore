package com.company.metier;

import javax.persistence.*;

@Entity
@Table(name = "Jeux", schema = "db_VideoGameStore", catalog = "")
public class JeuxEntity {
    private String platform;
    private Integer itemId;

    @Basic
    @Column(name = "Platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeuxEntity that = (JeuxEntity) o;

        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return platform != null ? platform.hashCode() : 0;
    }

    @Id
    @Column(name = "itemID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
