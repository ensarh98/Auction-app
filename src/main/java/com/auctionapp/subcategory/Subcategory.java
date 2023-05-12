package com.auctionapp.subcategory;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Subcategory in the system.
 *
 * @author Ensar Horozović
 */
@Getter
@Setter
public class Subcategory {

    private Integer id;

    private String name;

    private Integer categoryId;
}
