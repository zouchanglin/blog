package xpu.edu.blog.service;

import xpu.edu.blog.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoService {
    List<CategoryInfo> getAllCategory();

    CategoryInfo getById(Integer categoryId);

    /**
     * 对应分类的文章数量+1
     * @param categoryId 分类的Id
     */
    void addOneCategoryNum(Integer categoryId);
}
