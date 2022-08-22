package com.spenndify.application.spendylast.categories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
    @Transactional
    public void editCategory(EditCategoryRequest editCategoryRequest) {
        Category category = categoryRepository.findByCategoryName(editCategoryRequest.getCategoryName());
        if(editCategoryRequest.getCategoryName()!=null &&
                editCategoryRequest.getCategoryName().length()>0 &&
                !Objects.equals(category.getCategoryName(), editCategoryRequest.getCategoryName())){
            category.setCategoryName(editCategoryRequest.getCategoryName());
        }

        if(editCategoryRequest.getDescription()!=null && editCategoryRequest.getDescription().length()>0 &&
                !Objects.equals(category.getDescription(), editCategoryRequest.getDescription())){
            category.setDescription(editCategoryRequest.getDescription());
        }

        if(editCategoryRequest.getImageUrl()!=null && editCategoryRequest.getDescription().length()>0 &&
                !Objects.equals(category.getImageUrl(), editCategoryRequest.getImageUrl())){
            category.setImageUrl(editCategoryRequest.getImageUrl());
        }

    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}