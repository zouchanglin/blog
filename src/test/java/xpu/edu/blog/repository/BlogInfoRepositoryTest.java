package xpu.edu.blog.repository;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.BlogInfo;
import xpu.edu.blog.enums.BlogAuditEnum;
import xpu.edu.blog.utils.KeyUtil;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogInfoRepositoryTest {
    @Autowired
    private BlogInfoRepository repository;
    @Test
    public void save() throws IOException {

        File fileList = new File("/Users/zouchanglin/Desktop/hexo/source/_posts");
        File[] files = fileList.listFiles();
        assert files != null;
        for(File file: files){
            //System.out.println(file);
            BlogInfo blogInfo = new BlogInfo();
            blogInfo.setBlogId(KeyUtil.genUniqueKey());
            blogInfo.setBlogCategory(5);
            blogInfo.setBlogContent(FileUtils.readFileToString(file));
            blogInfo.setBlogAudit(BlogAuditEnum.RELEASE.getCode());
            String fileName = file.getName();
            String substring = fileName.substring(fileName.lastIndexOf("\\")+1);
            blogInfo.setBlogTitle(substring);
            repository.save(blogInfo);
        }
    }
}