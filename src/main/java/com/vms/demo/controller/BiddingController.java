package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.auctionBidding.BiddingCreateDTO;
import com.vms.demo.dto.auctionBidding.BiddingFullDTO;
import com.vms.demo.dto.auctionBidding.BiddingUpdateDTO;
import com.vms.demo.service.BiddingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("biddings")
public class BiddingController {
    @Autowired
    private BiddingService biddingService;

    @GetMapping
    public List<BiddingFullDTO> getAllBiddings() {
        return biddingService.getAllBiddings();
    }

    @GetMapping("{biddingID}")
    public BiddingFullDTO retrieve(@PathVariable Long biddingID) {
        return biddingService.getBiddingById(biddingID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BiddingFullDTO create(@Valid @RequestBody BiddingCreateDTO biddingCreateDTO) {
        return biddingService.createBidding(biddingCreateDTO);
    }

    @PutMapping("{biddingID}")
    @ResponseStatus(HttpStatus.OK)
    public BiddingFullDTO update(@PathVariable Long biddingID, @RequestBody BiddingUpdateDTO biddingUpdateDTO) {
        return biddingService.updateBidding(biddingID, biddingUpdateDTO);
    }

    @DeleteMapping("{biddingID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long biddingID) {
        biddingService.deleteBidding(biddingID);
    }
}
