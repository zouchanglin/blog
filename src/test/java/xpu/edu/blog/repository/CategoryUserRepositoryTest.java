package xpu.edu.blog.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.CategoryUser;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryUserRepositoryTest {
    @Autowired
    private CategoryUserRepository repository;

    @Test
    public void save(){
        CategoryUser categoryUser = new CategoryUser();
        categoryUser.setUserId("123456");
        categoryUser.setCategoryName("JavaEE框架");
        assertNotNull(repository.save(categoryUser));

        CategoryUser categoryUser1 = new CategoryUser();
        categoryUser1.setUserId("123456");
        categoryUser1.setCategoryName("C/C++");
        assertNotNull(repository.save(categoryUser1));

        CategoryUser categoryUser2 = new CategoryUser();
        categoryUser2.setUserId("123457");
        categoryUser2.setCategoryName("软件测试");
        assertNotNull(repository.save(categoryUser2));
    }

    @Test
    public void find(){
        List<CategoryUser> allByUserId = repository.findAllByUserId("123456");
        for(CategoryUser categoryUser: allByUserId){
            System.out.println(categoryUser);
        }
    }
}