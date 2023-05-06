package com.auctionapp.subcategory;

import com.auctionapp.category.CategoryDetail;
import com.auctionapp.common.AppException;
import com.auctionapp.db.model.SubcategoryRecord;
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
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Subcategory> getSubcategories() {
        var subcategoryList = subcategoryRepository.findAll();

        return subcategoryList.stream()
                .map(subcategory -> modelMapper.map(subcategory, Subcategory.class))
                .collect(Collectors.toList());
    }

    public Integer createSubcategory(SubcategoryDetail subcategory) {
        if (subcategory == null || subcategory.getName() == null || subcategory.getCategoryId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }
        var categoryRecord = categoryRepository.findById(subcategory.getCategoryId());
        if(!categoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID kategorije ne postoji u sistemu.");
        }

        var subcategoryRecord = new SubcategoryRecord();
        subcategoryRecord.setName(subcategory.getName());
        subcategoryRecord.setCategoryId(subcategory.getCategoryId());

        var subcategoryId = subcategoryRepository.save(subcategoryRecord).getId();
        return subcategoryId;
    }

    public void updateSubcategory(Integer subcategoryId, SubcategoryDetail subcategory) {
        if (subcategory == null || subcategory.getName() == null || subcategory.getCategoryId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }
        var categoryRecord = categoryRepository.findById(subcategory.getCategoryId());
        if(!categoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, "Prosljeđeni ID kategorije ne postoji u sistemu.");
        }

        var subcategoryRecord = subcategoryRepository.findById(subcategoryId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, "Zapis ne postoji u sistemu."));
        subcategoryRecord.setName(subcategory.getName());
        subcategoryRecord.setCategoryId(subcategory.getCategoryId());

        subcategoryRepository.save(subcategoryRecord);
    }

    public void deleteSubcategory(Integer subcategoryId) {
        if (subcategoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
        }

        subcategoryRepository.deleteById(subcategoryId);
    }
}
