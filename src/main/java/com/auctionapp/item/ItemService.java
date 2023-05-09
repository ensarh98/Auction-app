package com.auctionapp.item;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.ItemRecord;
import com.auctionapp.db.repository.BidRepository;
import com.auctionapp.db.repository.ItemRepository;
import com.auctionapp.db.repository.SubcategoryRepository;
import com.auctionapp.db.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private BidRepository bidRepository;

    public List<Item> getItems() {
        var itemList = itemRepository.findAll();

        return itemList.stream()
            .map(item -> modelMapper.map(item, Item.class))
            .collect(Collectors.toList());
    }

    public Integer createItem(Item item) {
        if(item == null || item.getName() == null || item.getStartPrice() == null || item.getStartDate() == null || item.getEndDate() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        if(item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR, "Početna cijena artikla ne smije biti negativna cifra.");
        }

        var userRecord = userRepository.findById(item.getUserId());
        if(!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID korisnika ne postoji u sistemu.");
        }

        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if(!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID podkategorije ne postoji u sistemu.");
        }

        Date today = new Date(); //Provjera da li je početni datum prodaje prije krajnjeg i trenutnog
        if(today.compareTo(item.getStartDate()) > 0 || item.getStartDate().compareTo(item.getEndDate()) > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, "Datumi prodaje nisu valjani.");
        }

        var itemRecord = new ItemRecord();
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhoto(item.getPhoto());
        itemRecord.setStartPrice(item.getStartPrice());
        itemRecord.setStartDate(item.getStartDate());
        itemRecord.setEndDate(item.getEndDate());
        itemRecord.setSubcategoryId(item.getSubcategoryId());
        itemRecord.setUserId(item.getUserId());

        var itemId = itemRepository.save(itemRecord).getId();
        return itemId;
    }

    public void updateItem(Integer itemId, Item item) {
        if(item == null || item.getName() == null || item.getStartPrice() == null || item.getStartDate() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        if(item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR, "Početna cijena artikla ne smije biti negativna cifra.");
        }

        var userRecord = userRepository.findById(item.getUserId());
        if(!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID korisnika ne postoji u sistemu.");
        }

        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if(!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID podkategorije ne postoji u sistemu.");
        }

        Date today = new Date(); //Provjera da li je početni datum prodaje prije krajnjeg i trenutnog
        if(today.compareTo(item.getStartDate()) > 0 || item.getStartDate().compareTo(item.getEndDate()) > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, "Datumi prodaje nisu valjani.");
        }

        var itemRecord = itemRepository.findById(itemId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Zapis ne postoji u sistemu."));
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhoto(item.getPhoto());
        itemRecord.setStartPrice(item.getStartPrice());
        itemRecord.setStartDate(item.getStartDate());
        itemRecord.setEndDate(item.getEndDate());
        itemRecord.setSubcategoryId(item.getSubcategoryId());
        itemRecord.setUserId(item.getUserId());

        itemRepository.save(itemRecord);
    }

    public void deleteItem(Integer itemId) {
        if (itemId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        var itemRecord = itemRepository.findById(itemId);
        if(!itemRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Zapis ne postoji u sistemu.");
        }

        var bidCounter = bidRepository.countBidByItemId(itemId);
        if (bidCounter > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, "Akcija nije moguća. Postoji bid pridru" +
                    "žen ovom itemu.");
        }

        itemRepository.deleteById(itemId);
    }

}
