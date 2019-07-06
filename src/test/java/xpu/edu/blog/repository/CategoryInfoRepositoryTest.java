package xpu.edu.blog.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.CategoryInfo;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryInfoRepositoryTest {
    @Autowired
    private CategoryInfoRepository repository;

    @Test
    public void save(){
        CategoryInfo categoryInfo0 = new CategoryInfo();
        categoryInfo0.setCategoryName("心智探寻");

        CategoryInfo categoryInfo1 = new CategoryInfo();
        categoryInfo1.setCategoryName("学习方法");

        CategoryInfo categoryInfo2 = new CategoryInfo();
        categoryInfo2.setCategoryName("计算机科学");

        CategoryInfo categoryInfo3 = new CategoryInfo();
        categoryInfo3.setCategoryName("机器学习");

        CategoryInfo categoryInfo4 = new CategoryInfo();
        categoryInfo4.setCategoryName("编程");

        CategoryInfo categoryInfo5 = new CategoryInfo();
        categoryInfo5.setCategoryName("算法");

        CategoryInfo categoryInfo6 = new CategoryInfo();
        categoryInfo6.setCategoryName("数学");

        assertNotNull(repository.save(categoryInfo0));
        assertNotNull(repository.save(categoryInfo1));
        assertNotNull(repository.save(categoryInfo2));
        assertNotNull(repository.save(categoryInfo3));
        assertNotNull(repository.save(categoryInfo4));
        assertNotNull(repository.save(categoryInfo5));
        assertNotNull(repository.save(categoryInfo6));
    }
}