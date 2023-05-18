package com.auctionapp.controller;

import com.auctionapp.attachment.AttachmentService;
import com.auctionapp.item.Item;
import com.auctionapp.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private AttachmentService attachmentService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("productImage") MultipartFile file, Integer id) throws IOException {
        attachmentService.uploadImage(file, id);
    }

    @GetMapping("/download/{id}")
    public byte[] downloadImage(@PathVariable Integer id) {
        byte[] image = attachmentService.downloadImage(id);
        return image;
    }
}
