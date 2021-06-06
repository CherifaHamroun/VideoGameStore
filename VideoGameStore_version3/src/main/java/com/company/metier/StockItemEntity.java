package com.company.metier;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "StockItem", schema = "db_VideoGameStore")
public class StockItemEntity {
    private Integer itemId;
    private String title;
    private Double rentalPrice;
    private RentedItemEntity itemID;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "itemID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "rentalPrice")
    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockItemEntity that = (StockItemEntity) o;

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (rentalPrice != null ? !rentalPrice.equals(that.rentalPrice) : that.rentalPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (rentalPrice != null ? rentalPrice.hashCode() : 0);
        return result;
    }
}
