package xpu.edu.blog.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.UserInfo;
import xpu.edu.blog.utils.KeyUtil;

import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void save(){
        for (int i = 0; i < 4; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(KeyUtil.genUniqueKey());
            userInfo.setUserEmail("12345"+i+"@qq.com");
            userInfo.setUserPassword("123456");
            userInfo.setUserName("Tim"+i);
            UserInfo save = repository.save(userInfo);
            assertNotNull(save);
        }
    }

    @Test
    public void find(){
        UserInfo byUserEmailAndUserPassword = repository.findByUserEmailAndUserPassword("123452@qq.com", "123456");
        log.info("byUserEmailAndUserPassword={}", byUserEmailAndUserPassword);
        assertNotNull(byUserEmailAndUserPassword);

        UserInfo byUserIdAndUserPassword = repository.findByUserIdAndUserPassword("", "123456");
        log.info("byUserIdAndUserPassword={}", byUserIdAndUserPassword);
        assertNotNull(byUserIdAndUserPassword);
    }
}