package com.auctionapp.controller;

import com.auctionapp.attachment.Attachment;
import com.auctionapp.attachment.AttachmentService;
import com.auctionapp.common.AppException;
import com.auctionapp.db.model.AttachmentRecord;
import com.auctionapp.db.repository.UserRepository;
import com.auctionapp.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController extends BaseController {

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
