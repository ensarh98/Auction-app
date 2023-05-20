package com.auctionapp.controller;

import com.auctionapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/{id}/photo")
    public void uploadImage(@PathVariable Integer id, @RequestParam MultipartFile file) throws IOException {
        userService.uploadProfilePhoto(id, file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable Integer id) {
        var photo = userService.getUserProfilePhoto(id);
        return buildDownloadResponse(photo);
    }
}
