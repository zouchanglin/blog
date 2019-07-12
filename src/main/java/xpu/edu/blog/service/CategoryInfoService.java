package xpu.edu.blog.service;

import xpu.edu.blog.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoService {

    /**
     * 获取所有分类信息
     * @return 所有分类信息
     */
    List<CategoryInfo> getAllCategory();

    /**
     * 根据Id获取分类信息
     * @param categoryId 分类Id
     * @return 分类信息
     */
    CategoryInfo getById(Integer categoryId);

    /**
     * 对应分类的文章数量+1
     * @param categoryId 对应分类的Id
     */
    void addOneCategoryNum(Integer categoryId);
}
