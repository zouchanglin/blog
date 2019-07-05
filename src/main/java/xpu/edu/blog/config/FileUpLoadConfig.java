package xpu.edu.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "fileupload")
@Component
public class FileUpLoadConfig {
    /**
     * 项目的url
     */
    public String blogUrl;

    /**
     * 图片存储路径
     */
    private String imgPath;
}
