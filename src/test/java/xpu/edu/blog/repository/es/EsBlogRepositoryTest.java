package xpu.edu.blog.repository.es;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.blog.entity.es.EsBlog;
import xpu.edu.blog.utils.KeyUtil;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository repository;

    @Before
    public void initEsBlogRepository(){
        //清除所有数据
        repository.deleteAll();

        EsBlog esBlog1 = new EsBlog();
        esBlog1.setId(KeyUtil.genUniqueKey());
        esBlog1.setTitle("行宫");
        esBlog1.setSummary("寥落古行宫");
        esBlog1.setContent("寥落古行宫，宫花寂寞红。\n白头宫女宫，闲坐说玄宗。");

        EsBlog esBlog2 = new EsBlog();
        esBlog2.setId(KeyUtil.genUniqueKey());
        esBlog2.setTitle("竹里馆");
        esBlog2.setSummary("独坐幽篁里");
        esBlog2.setContent("独坐幽篁里，弹琴复长啸。\n深林人不知，明月来相照。");

        EsBlog esBlog3 = new EsBlog();
        esBlog3.setId(KeyUtil.genUniqueKey());
        esBlog3.setTitle("静夜思");
        esBlog3.setSummary("床前明月光");
        esBlog3.setContent("床前明月光，疑是地上霜。\n举头望不月，低头思故乡。");

        repository.save(esBlog1);
        repository.save(esBlog2);
        repository.save(esBlog3);

    }
    @Test
    public void find() {
        final String TAG = "月";
        Pageable pageable = PageRequest.of(0, 20);
        Page<EsBlog> findRet = repository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(TAG, TAG, TAG, pageable);
        for(EsBlog esBlog: findRet.getContent()){
            log.info("EsBlog={}", esBlog);
        }
        assertEquals(findRet.getTotalElements(), 2);
    }
}