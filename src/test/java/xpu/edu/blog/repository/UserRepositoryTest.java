package xpu.edu.blog.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.UserInfo;
import xpu.edu.blog.utils.KeyUtil;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void save(){
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(KeyUtil.genUniqueKey());
            userInfo.setUserEmail("167831278@qq.com");
            userInfo.setUserName("Tim");

            UserInfo save = repository.save(userInfo);
            assertNotNull(save);
        }
    }
}