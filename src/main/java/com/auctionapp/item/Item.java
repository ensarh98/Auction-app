package com.auctionapp.item;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Item {

    private Integer id;

    private String name;

    private String description;

    private String address;

    private String photo;

    private Double startPrice;

    private Date startDate;

    private Date endDate;

    private Integer subcategoryId;

    private Integer userId;
}
