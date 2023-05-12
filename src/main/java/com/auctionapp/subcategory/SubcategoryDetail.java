package com.auctionapp.subcategory;

/**
 * Represents a Subcategory in the system.
 *
 * @author Ensar Horozović
 */
public class SubcategoryDetail {
    private String name;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
