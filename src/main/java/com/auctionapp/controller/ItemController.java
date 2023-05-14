package com.auctionapp.controller;

import com.auctionapp.item.Item;
import com.auctionapp.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/items")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @GetMapping()
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping()
    public Integer createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Integer id, @RequestBody Item item) {
        itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }
}
