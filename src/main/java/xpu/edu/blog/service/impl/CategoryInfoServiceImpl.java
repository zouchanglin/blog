package xpu.edu.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.CategoryInfo;
import xpu.edu.blog.enums.ResultEnum;
import xpu.edu.blog.exception.BlogException;
import xpu.edu.blog.repository.CategoryInfoRepository;
import xpu.edu.blog.service.CategoryInfoService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryInfoServiceImpl implements CategoryInfoService {
    @Autowired
    private CategoryInfoRepository categoryInfoRep;

    @Override
    public List<CategoryInfo> getAllCategory() {
        return categoryInfoRep.findAll();
    }

    @Override
    public CategoryInfo getById(Integer categoryId) {
        return categoryInfoRep.findById(categoryId).orElse(null);
    }

    @Override
    public void addOneCategoryNum(Integer categoryId) {
        Optional<CategoryInfo> byId = categoryInfoRep.findById(categoryId);
        if(byId.isPresent()){
            CategoryInfo categoryInfo = byId.get();
            categoryInfo.setCategoryNum(categoryInfo.getCategoryNum() + 1);
            CategoryInfo save = categoryInfoRep.save(categoryInfo);
            log.info("CategoryInfoServiceImpl addOneCategoryNum save={}", save);
        }else{
            throw new BlogException(ResultEnum.PARAM_ERROR);
        }
    }
}
