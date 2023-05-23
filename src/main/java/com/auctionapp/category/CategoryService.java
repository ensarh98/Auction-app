package com.auctionapp.category;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.CategoryRecord;
import com.auctionapp.db.repository.CategoryRepository;
import com.auctionapp.db.repository.SubcategoryRepository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryService class for managing category information.
 * Provides methods for creating, updating, deleting and retrieving user information.
 * Uses CategoryRepository for data access.
 *
 * @author Ensar Horozović
 */
@Transactional
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public List<Category> getCategories() {
        var categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, Category.class))
                .collect(Collectors.toList());
    }

    public Integer createCategory(CategoryDetail category) {
        if (category == null || category.getName() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var categoryRecord = new CategoryRecord();
        categoryRecord.setName(category.getName());

        var categoryId = categoryRepository.save(categoryRecord).getId();
        return categoryId;
    }

    public void updateCategory(Integer categoryId, CategoryDetail category) {
        if (category == null || category.getName() == null || categoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var categoryRecord = categoryRepository.findById(categoryId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));
        categoryRecord.setName(category.getName());

        categoryRepository.save(categoryRecord);
    }

    public void deleteCategory(Integer categoryId) {
        if (categoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }
        var subcategoryCounter = subcategoryRepository.countByCategoryId(categoryId);
        if (subcategoryCounter > 0) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("SUBCATEGORY_EXIST", null, LocaleContextHolder.getLocale()));
        }
        categoryRepository.deleteById(categoryId);
    }

}
