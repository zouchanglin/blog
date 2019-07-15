package xpu.edu.blog.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.enums.BlogAuditEnum;
import xpu.edu.blog.service.BlogService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceImplTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void findSomeBlogByThis() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setBlogCategory(5);
        blogInfo.setBlogId("156299185450586");
        blogInfo.setBlogAudit(BlogAuditEnum.RELEASE.getCode());
        List<BlogInfo> someBlogByThis = blogService.findSomeBlogByThis(blogInfo);
        for(BlogInfo blogInfo1: someBlogByThis){
            System.out.println(blogInfo1.getBlogTitle());
        }
    }
}