package com.auctionapp.bid;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.BidRecord;
import com.auctionapp.db.repository.BidRepository;
import com.auctionapp.db.repository.ItemRepository;
import com.auctionapp.db.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Bid> getBids() {
        var bidList = bidRepository.findAll();

        return bidList.stream()
                .map(bid -> modelMapper.map(bid, Bid.class))
                .collect(Collectors.toList());
    }

    public Integer createBid(Bid bid) {
        if (bid == null || bid.getBidPrice() == null || bid.getUserId() == null || bid.getItemId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        var userRecord = userRepository.findById(bid.getUserId());
        if(!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID korisnika ne postoji u sistemu.");
        }

        var itemRecord = itemRepository.findById(bid.getItemId());
        if(!itemRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID artikla ne postoji u sistemu.");
        }

        var bidRecordExist = bidRepository.findById(bid.getId());
        if(bidRecordExist.isPresent()) {
            if(bidRepository.getBidPrice(bid.getId()) >= bid.getBidPrice()) {
                throw new AppException(AppException.VALIDATION_ERROR, "Ponuđena cijena mora biti veća od cijene artikla.");
            }
            bid.setBids(bidRepository.getBidBids(bid.getId())+1);
            updateBid(bid.getId(), bid);
            return bid.getId();
        }

        var bidRecord = new BidRecord();
        bidRecord.setUserId(bid.getUserId());
        bidRecord.setItemId(bid.getItemId());
        bidRecord.setBidPrice(bid.getBidPrice());
        bidRecord.setBids(0);
        bidRecord.setPurchased(false);

        var bidId = bidRepository.save(bidRecord).getId();
        return bidId;
    }

    public void updateBid(Integer bidId, Bid bid) {
        if (bid == null || bid.getBidPrice() == null || bid.getUserId() == null || bid.getItemId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        userRepository.findById(bid.getUserId()).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Prosljeđeni ID korisnika ne postoji u sistemu."));

        itemRepository.findById(bid.getItemId()).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Prosljeđeni ID artikla ne postoji u sistemu."));;

        var bidRecord = bidRepository.findById(bidId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Zapis ne postoji u sistemu."));
        bidRecord.setUserId(bid.getUserId());
        bidRecord.setItemId(bid.getItemId());
        bidRecord.setBidPrice(bid.getBidPrice());
        bidRecord.setBids(bid.getBids());
        bidRecord.setPurchased(bid.getPurchased());

        bidRepository.save(bidRecord);
    }

    public void deleteBid(Integer bidId) {
        if (bidId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }
        bidRepository.findById(bidId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Zapis ne postoji u sistemu."));

        bidRepository.deleteById(bidId);
    }
}
