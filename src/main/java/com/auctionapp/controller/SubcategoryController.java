package com.auctionapp.controller;

import com.auctionapp.subcategory.Subcategory;
import com.auctionapp.subcategory.SubcategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController extends BaseController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping()
    public List<Subcategory> getSubcategories() {
        return subcategoryService.getSubcategories();
    }


}
