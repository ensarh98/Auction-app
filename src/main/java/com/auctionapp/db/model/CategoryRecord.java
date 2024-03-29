package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a category in the system.
 * This entity is mapped to the "categories" table in the database.
 *
 * @author Ensar Horozović
 */
@Entity()
@Table(schema = "core", name = "categories")
@Getter
@Setter
public class CategoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}

