package xpu.edu.blog.service;

import xpu.edu.blog.entity.CategoryDetail;

import java.util.List;

public interface CategoryDetailService {
    List<CategoryDetail> findAllByMaster(Integer master);
    List<CategoryDetail> findAll();
}
