package com.auctionapp.item;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.ItemRecord;
import com.auctionapp.db.repository.BidRepository;
import com.auctionapp.db.repository.ItemRepository;
import com.auctionapp.db.repository.SubcategoryRepository;
import com.auctionapp.db.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @Autowired
    private MessageSource messageSource;

    public List<Item> getItems() {
        var itemList = itemRepository.findAll();

        return itemList.stream()
            .map(item -> modelMapper.map(item, Item.class))
            .collect(Collectors.toList());
    }

    public Integer createItem(Item item) {
        if(item == null || item.getName() == null || item.getStartPrice() == null || item.getStartDate() == null || item.getEndDate() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        if(item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("NEGATIVE_ITEM_PRICE", null, LocaleContextHolder.getLocale()));
        }

        var userRecord = userRepository.findById(item.getUserId());
        if(!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_USER_ID", null, LocaleContextHolder.getLocale()));
        }
        System.out.println("TUSAM");
        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if(!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_SUBCATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

        Date today = new Date(); //Provjera da li je početni datum prodaje prije krajnjeg i trenutnog
        if(today.compareTo(item.getStartDate()) > 0 || item.getStartDate().compareTo(item.getEndDate()) > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("DATE_NOT_VALID", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = new ItemRecord();
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhotoId(item.getPhotoId());
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
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        if(item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("NEGATIVE_ITEM_PRICE", null, LocaleContextHolder.getLocale()));
        }

        var userRecord = userRepository.findById(item.getUserId());
        if(!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_USER_ID", null, LocaleContextHolder.getLocale()));
        }

        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if(!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_SUBCATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

        Date today = new Date(); //Provjera da li je početni datum prodaje prije krajnjeg i trenutnog
        if(today.compareTo(item.getStartDate()) > 0 || item.getStartDate().compareTo(item.getEndDate()) > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("DATE_NOT_VALID", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = itemRepository.findById(itemId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhotoId(item.getPhotoId());
        itemRecord.setStartPrice(item.getStartPrice());
        itemRecord.setStartDate(item.getStartDate());
        itemRecord.setEndDate(item.getEndDate());
        itemRecord.setSubcategoryId(item.getSubcategoryId());
        itemRecord.setUserId(item.getUserId());

        itemRepository.save(itemRecord);
    }

    public void deleteItem(Integer itemId) {
        if (itemId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = itemRepository.findById(itemId);
        if(!itemRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale()));
        }

        var bidCounter = bidRepository.countBidByItemId(itemId);
        if (bidCounter > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("BID_EXIST", null, LocaleContextHolder.getLocale()));
        }

        itemRepository.deleteById(itemId);
    }

}
