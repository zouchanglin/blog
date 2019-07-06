package xpu.edu.blog.service;

import xpu.edu.blog.entity.CategoryUser;

import java.util.List;

public interface CategoryUserService {
    List<CategoryUser> getAllCategoryUser();

    List<CategoryUser> getAllCategoryByUserId(String userId);

    void addOneCategoryNum(Integer blogUserCategory, String userId);
}
