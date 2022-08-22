package com.spenndify.application.spendylast.categories;


import com.spenndify.application.spendylast.categories.config.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody EditCategoryRequest editCategoryRequest){
        if (Objects.nonNull(categoryService.readCategory(editCategoryRequest.getCategoryName()))) {
            categoryService.editCategory(editCategoryRequest);
            return new ResponseEntity<>(new ApiResponse(true, "category has been successfully updated"), HttpStatus.CREATED);
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category you are trying to edit is non-existent"), HttpStatus.CONFLICT);
    }

    @GetMapping("/list")
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

}
