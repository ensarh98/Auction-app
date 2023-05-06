package com.auctionapp.subcategory;

import com.auctionapp.db.repository.SubcategoryRepository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Subcategory> getSubcategories() {
        var subcategoryList = subcategoryRepository.findAll();

        return subcategoryList.stream()
                .map(subcategory -> modelMapper.map(subcategory, Subcategory.class))
                .collect(Collectors.toList());
    }

}
