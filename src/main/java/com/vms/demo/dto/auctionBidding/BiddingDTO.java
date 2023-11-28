package com.vms.demo.dto.auctionBidding;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.BiddingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BiddingDTO {
    private Long biddingID;
    private Long carID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    // Car Imgs
    /**
     * the status of the bidding. Can be one of the following: open, closed, hidden
     */
    private BiddingStatus status;
    private String info;
}
