package xpu.edu.blog.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.CategoryDetail;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryDetailRepositoryTest {
    @Autowired
    private CategoryDetailRepository repository;

    @Test
    public void findAllByDetailMaster() {
        List<CategoryDetail> allByDetailMaster = repository.findAllByDetailMaster(1);
        for(CategoryDetail categoryDetail: allByDetailMaster){
            System.out.println(categoryDetail);
        }
    }
}