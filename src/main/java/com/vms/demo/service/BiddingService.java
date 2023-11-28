package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vms.demo.dto.auctionBidding.BiddingCreateDTO;
import com.vms.demo.dto.auctionBidding.BiddingFullDTO;
import com.vms.demo.dto.auctionBidding.BiddingUpdateDTO;
import com.vms.demo.entity.BiddingEntity;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.repository.BiddingRepository;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.types.BiddingStatus;
import com.vms.demo.types.CarStatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Autowired
    private CarRepository carRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<BiddingFullDTO> getAllBiddings() {
        List<BiddingEntity> biddings = biddingRepository.findAll();
        return modelMapper.map(biddings, new TypeToken<List<BiddingFullDTO>>() {
        }.getType());
    }

    public BiddingFullDTO getBiddingById(Long biddingID) {
        Optional<BiddingEntity> biddingOptional = biddingRepository.findById(biddingID);
        if (!biddingOptional.isPresent()) {
            throw new EntityNotFoundException("Bidding not found with id: " + biddingID);
        }
        BiddingEntity bidding = biddingOptional.get();
        BiddingFullDTO biddingDTO = modelMapper.map(bidding, BiddingFullDTO.class);
        return biddingDTO;
    }

    public BiddingFullDTO createBidding(BiddingCreateDTO biddingCreateDTO) {
        BiddingEntity bidding = modelMapper.map(biddingCreateDTO, BiddingEntity.class);
        bidding.setBiddingID(null);
        System.out.println(bidding.getCar());
        Long carID = biddingCreateDTO.getCarID();
        if (carID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Car id is required to create a bidding");
        }
        Optional<CarEntity> carOptional = carRepository.findById(carID);
        if (!carOptional.isPresent()) {
            throw new EntityNotFoundException("Car not found with id: " + carID);
        }
        CarEntity car = carOptional.get();
        if (car.getDriver() != null) {
            // Assume car status is Active
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested car is assigned to a driver with id = " + car.getDriver().getUserID());
        }
        if (car.getStatus() == CarStatus.SOLD) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested car is already sold");
        }
        if (bidding.getStatus() == BiddingStatus.CLOSED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Closed bidding cannot be created");
        }
        bidding.setDateCreated(ZonedDateTime.now());
        bidding.setCar(car);
        bidding = biddingRepository.save(bidding);
        if (car.getStatus() != CarStatus.ON_AUCTION) {
            car.setStatus(CarStatus.ON_AUCTION);
            carRepository.save(car);
        }
        BiddingFullDTO dto = modelMapper.map(bidding, BiddingFullDTO.class);
        return dto;
    }

    public BiddingFullDTO updateBidding(Long biddingID, BiddingUpdateDTO biddingUpdateDTO) {
        Optional<BiddingEntity> biddingOptional = biddingRepository.findById(biddingID);
        if (!biddingOptional.isPresent()) {
            throw new EntityNotFoundException("Bidding not found with id: " + biddingID);
        }
        BiddingEntity bidding = biddingOptional.get();
        CarEntity car = bidding.getCar();

        if (bidding.getStatus() == BiddingStatus.CLOSED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Closed bidding cannot be updated");
        }
        if (biddingUpdateDTO.getStatus() != null) {
            bidding.setStatus(biddingUpdateDTO.getStatus());
        }
        if (biddingUpdateDTO.getInfo() != null) {
            bidding.setInfo(biddingUpdateDTO.getInfo());
        }
        bidding = biddingRepository.save(bidding);
        if (car != null) {
            if (bidding.getStatus() == BiddingStatus.CLOSED) {
                car.setStatus(CarStatus.SOLD);
                carRepository.save(car);
            }
        }
        BiddingFullDTO biddingDTO = modelMapper.map(bidding, BiddingFullDTO.class);
        return biddingDTO;
    }

    public void deleteBidding(Long biddingID) {
        biddingRepository.deleteById(biddingID);
    }
}