package com.auctionapp.category;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.CategoryRecord;
import com.auctionapp.db.repository.CategoryRepository;
import com.auctionapp.db.repository.SubcategoryRepository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Category> getCategories() {
        var categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, Category.class))
                .collect(Collectors.toList());
    }

    public Integer createCategory(CategoryDetail category) {
        if (category == null || category.getName() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        var categoryRecord = new CategoryRecord();
        categoryRecord.setName(category.getName());

        var categoryId = categoryRepository.save(categoryRecord).getId();
        return categoryId;
    }

    public void updateCategory(Integer categoryId, CategoryDetail category) {
        if (category == null || category.getName() == null || categoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        var categoryRecord = categoryRepository.findById(categoryId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Zapis ne postoji u sistemu."));
        categoryRecord.setName(category.getName());

        categoryRepository.save(categoryRecord);
    }

    public void deleteCategory(Integer categoryId) {
        if (categoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        categoryRepository.deleteById(categoryId);
    }

}
