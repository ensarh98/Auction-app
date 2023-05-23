package com.auctionapp.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a bid in the system.
 * This entity is mapped to the "bids" table in the database.
 *
 * @author Ensar Horozović
 */
@Entity()
@Table(schema = "core", name = "bids")
@Getter
@Setter
public class BidRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Double bidPrice;

    private Integer itemId;

    private Integer bids;

    private Boolean purchased;
}
