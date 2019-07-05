package xpu.edu.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xpu.edu.blog.config.FileUpLoadConfig;
import xpu.edu.blog.enums.ResultEnum;
import xpu.edu.blog.exception.BlogException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileUpLoadConfig fileUpLoadConfig;

    @GetMapping("/fileDownload")
    public ResponseEntity<FileSystemResource> file(@RequestParam("fileUrl") String fileName) {
        return export(new File(fileUpLoadConfig.getImgPath()+fileName));
    }

    @ResponseBody
    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
            throw new BlogException(ResultEnum.NULL_FILE);
        }
        String fileName = file.getOriginalFilename();  // 文件名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = fileUpLoadConfig.getImgPath(); // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();
            assert mkdirs;
        }

        JSONObject saveResult = new JSONObject();
        try {
            file.transferTo(dest);
            String fileUrl = fileUpLoadConfig.getBlogUrl() + "/file/fileDownload?fileUrl=" + fileName;
            saveResult.put("success", 1);
            saveResult.put("message", "上传成功");
            saveResult.put("url", fileUrl);
            return saveResult.toJSONString();
        } catch (IOException e) {
            saveResult.put("success", 0);
            saveResult.put("message", "上传失败");
            saveResult.put("url", null);
            e.printStackTrace();
            return saveResult.toJSONString();
        }
    }


    private ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-ranges", "bytes");
        headers.add("cache-control", "max-age=2592000");
        headers.add("expires", new Date().toString());
        headers.add("last-modified", new Date().toString());
        headers.add("etag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("image/png")) .body(new FileSystemResource(file));
    }
}