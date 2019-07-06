package xpu.edu.blog.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.utils.KeyUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BlogInfoRepositoryTest {
    @Autowired
    private BlogInfoRepository repository;

    @Test
    public void save() throws IOException {
        for (int i = 0; i < 20; i++) {
            BlogInfo blogInfo = new BlogInfo();
            blogInfo.setBlogId(KeyUtil.genUniqueKey());
            blogInfo.setBlogTitle("理解RAII"+i);
            blogInfo.setBlogSummary("本文主要是C++的智能指针的实现原理！");
            blogInfo.setBlogContent(FileUtils.readFileToString(new File("/Users/zouchanglin/Desktop/test.md")));
            blogInfo.setAuthorId("123456");
            BlogInfo save = repository.save(blogInfo);
            assertNotNull(save);
            FileUtils.writeStringToFile(new File("/Users/zouchanglin/Desktop/test.html"),
                    blogInfo.getBlogHtmlContent(), "UTF-8");
        }
    }

    @Test
    public void findMyAllBlog(){
        List<BlogInfo> blogInfoList = repository.findAllByAuthorId("123456");
        for(BlogInfo blogInfo: blogInfoList){
            System.out.println(blogInfo);
        }
    }
}