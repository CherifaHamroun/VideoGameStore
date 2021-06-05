package com.company.metier;

import javax.persistence.*;

@Entity
@Table(name = "Film", schema = "db_VideoGameStore", catalog = "")
public class FilmEntity {
    private String actor;
    private Integer itemId;

    @Basic
    @Column(name = "actor")
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (actor != null ? !actor.equals(that.actor) : that.actor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return actor != null ? actor.hashCode() : 0;
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
