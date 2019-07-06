package xpu.edu.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.CategoryUser;
import xpu.edu.blog.enums.ResultEnum;
import xpu.edu.blog.exception.BlogException;
import xpu.edu.blog.repository.CategoryUserRepository;
import xpu.edu.blog.service.CategoryUserService;

import java.util.List;

@Service
@Slf4j
public class CategoryUserServiceImpl implements CategoryUserService {
    @Autowired
    private CategoryUserRepository categoryUserRep;

    @Override
    public List<CategoryUser> getAllCategoryUser() {
        return categoryUserRep.findAll();
    }

    @Override
    public List<CategoryUser> getAllCategoryByUserId(String userId) {
        return categoryUserRep.findAllByUserId(userId);
    }

    @Override
    public void addOneCategoryNum(Integer blogUserCategory, String userId) {
        CategoryUser categoryUser = categoryUserRep.findById(blogUserCategory).orElse(null);
        assert categoryUser != null;
        if(!categoryUser.getUserId().equals(userId)) throw new BlogException(ResultEnum.PARAM_ERROR);
        categoryUser.setCategoryNum(categoryUser.getCategoryNum() + 1);

        CategoryUser save = categoryUserRep.save(categoryUser);
        log.info("CategoryUserServiceImpl addOneCategoryNum save={}", save);
    }
}
