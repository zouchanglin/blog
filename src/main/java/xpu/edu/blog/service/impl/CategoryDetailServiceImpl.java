package xpu.edu.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.CategoryDetail;
import xpu.edu.blog.repository.CategoryDetailRepository;
import xpu.edu.blog.service.CategoryDetailService;

import java.util.List;

@Service
public class CategoryDetailServiceImpl implements CategoryDetailService {
    @Autowired
    private CategoryDetailRepository categoryDetailRepository;

    @Override
    public List<CategoryDetail> findAllByMaster(Integer master) {
        return categoryDetailRepository.findAllByDetailMaster(master);
    }

    @Override
    public List<CategoryDetail> findAll() {
        return categoryDetailRepository.findAll();
    }
}
