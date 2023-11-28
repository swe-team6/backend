package com.vms.demo.dto.auctionBidding;

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
public class BiddingUpdateDTO {
    // private Long carID;
    // Car Imgs
    /**
     * the status of the bidding. Can be one of the following: open, closed, hidden
     */
    private BiddingStatus status;
    private String info;
}
