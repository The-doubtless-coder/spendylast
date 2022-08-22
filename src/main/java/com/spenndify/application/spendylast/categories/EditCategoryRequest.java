package com.spenndify.application.spendylast.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCategoryRequest {
//    private Integer id;
    private String categoryName;
    private String description;
    private String imageUrl;
}
