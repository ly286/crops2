package com.dly.controller;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Tag(name = "Minio测试")
@RestController
@RequestMapping("/upload")
public class UploadController {

    static final Logger log = LoggerFactory.getLogger(UploadController.class);

    // 日期格式化
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyy/MM/dd/");

    // 资源的 访问 URL
    @Value("${minio.endpoint}")
    private String baseUrl;

    // API 端点
    @Value("${minio.endpoint}")
    private String endpoint;

    // Bucket 存储桶
    @Value("${minio.bucketName}")
    private String bucket;

    // Acess Key
    @Value("${minio.accessKey}")
    private String accessKey;

    // Secret Key
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 上传文件到 Minio 服务器，返回访问地址
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception{
        
        // 文件大小
        long size = file.getSize();
        if (size == 0) {
            return ResponseEntity.badRequest().body("禁止上传空文件");
        }
        
        // 文件名称
        String fileName = file.getOriginalFilename();
        
        // 文件后缀
        String ext = "";
        
        int index = fileName.lastIndexOf(".");
        if (index ==-1) {
            return ResponseEntity.badRequest().body("禁止上传无后缀的文件");
        }
        
        ext = fileName.substring(index);

        // 文件类型
        String contentType = file.getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        
        // 根据日期打散目录，使用 UUID 重命名文件
        String filePath = formatter.format(LocalDate.now()) + 
                        UUID.randomUUID().toString().replace("-", "") + 
                        ext;
        
        log.info("文件名称：{}", fileName);
        log.info("文件大小：{}", size);
        log.info("文件类型：{}", contentType);
        log.info("文件路径：{}", filePath);
        
        // 实例化客户端
        MinioClient client = MinioClient.builder()
                .endpoint(this.endpoint)
                .credentials(this.accessKey, this.secretKey)
                .build();

        
        // 上传文件到客户端
        try (InputStream inputStream = file.getInputStream()){
            client.putObject(PutObjectArgs.builder()
                    .bucket(this.bucket)		// 指定 Bucket 
                    .contentType(contentType)	// 指定 Content Type
                    .object(filePath)			// 指定文件的路径
                    .stream(inputStream, size, -1) // 文件的 Inputstream 流
                    .build());
        }
        
        
        // 返回最终的访问路径
        return ResponseEntity.ok(this.baseUrl + this.bucket + filePath);
    }
}