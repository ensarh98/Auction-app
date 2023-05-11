package com.auctionapp.bid;

import lombok.Getter;
import lombok.Setter;

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
