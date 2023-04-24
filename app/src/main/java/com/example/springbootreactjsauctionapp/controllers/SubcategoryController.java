package com.example.springbootreactjsauctionapp.controllers;

import com.example.springbootreactjsauctionapp.models.Category;
import com.example.springbootreactjsauctionapp.models.Subcategory;
import com.example.springbootreactjsauctionapp.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping("")
    public List<Subcategory> getSubcategories() {
        return subcategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategory(@PathVariable(value = "id") Long id) {
        return subcategoryRepository.findById(id)
                .map(subcategory -> new ResponseEntity<>(subcategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory) {
        Subcategory savedSubcategory = subcategoryRepository.save(subcategory);
        return new ResponseEntity<>(savedSubcategory, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable(value = "id") Long id, @RequestBody Subcategory updatedSubcategory) {
        return subcategoryRepository.findById(id)
                .map(subcategory -> {
                    updatedSubcategory.setId(id);
                    Subcategory savedSubategory = subcategoryRepository.save(updatedSubcategory);
                    return new ResponseEntity<>(savedSubategory, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        return subcategoryRepository.findById(id)
                .map(subcategory -> {
                    subcategoryRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
