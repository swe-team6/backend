package com.vms.demo.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuctionBidding {
    private Long biddingID;
    // carID
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    // Car Imgs
    /**
     * the status of the bidding. Can be one of the following: open, closed, hidden
     */
    // private BiddingStatus status;
    private String info;
}
