package com.company.metier;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RentedItem", schema = "db_VideoGameStore")
public class RentedItemEntity {
    private Integer customerId;
    private Integer itemId;
    private Date dueDate;
    private StockItemEntity itemID;

    @Basic
    @Column(name = "customerID")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Id
    @Basic
    @Column(name = "itemID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "dueDate")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentedItemEntity that = (RentedItemEntity) o;

        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }
}
