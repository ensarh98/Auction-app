package com.example.springbootreactjsauctionapp.controllers;

import com.example.springbootreactjsauctionapp.models.Bid;
import com.example.springbootreactjsauctionapp.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidRepository bidRepository;

    @GetMapping("")
    public List<Bid> getBids() {
        return bidRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable(value = "id") Long id) {
        return bidRepository.findById(id)
                .map(bid -> new ResponseEntity<>(bid, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
        Bid savedBid = bidRepository.save(bid);
        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable("id") Long id, @RequestBody Bid updatedBid) {
        return bidRepository.findById(id)
                .map(bid -> {
                    updatedBid.setId(id);
                    Bid savedBid = bidRepository.save(updatedBid);
                    return new ResponseEntity<>(savedBid, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable Long id) {
        return bidRepository.findById(id)
                .map(bid -> {
                    bidRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
