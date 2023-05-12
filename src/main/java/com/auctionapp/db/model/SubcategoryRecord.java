package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a subcategory in the system.
 * This entity is mapped to the "subcategories" table in the database.
 *
 * @author Ensar Horozović
 */
@Entity()
@Table(schema = "core", name = "subcategories")
@Getter
@Setter
public class SubcategoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer categoryId;
}
