package com.spenndify.application.spendylast.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
//    @Query("SELECT s FROM Category s WHERE s.spendUser = ?1")
//    List<Category> findAll(Integer id);



}