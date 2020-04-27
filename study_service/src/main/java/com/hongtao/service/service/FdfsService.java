package com.hongtao.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface FdfsService {

    public String fileUpload(MultipartFile file);  //文件上传 返回路径

    public Integer deleteFile(String filePath);  //删除文件

}
