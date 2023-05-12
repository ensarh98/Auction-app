package com.auctionapp.bid;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Bid in the system.
 *
 * @author Ensar Horozović
 */
@Getter
@Setter
public class Bid {

    private Integer id;

    private Integer userId;

    private Double bidPrice;

    private Integer itemId;

    private Integer bids;

    private Boolean purchased;
}
