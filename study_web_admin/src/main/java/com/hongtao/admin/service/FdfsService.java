package com.hongtao.admin.service;

import com.hongtao.admin.configer.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "study-service",configuration = FeignMultipartSupportConfig.class)
public interface FdfsService {

    //文件上传
    @RequestMapping(method = RequestMethod.POST, value = "api/vedio/fileUpload",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestPart("file") MultipartFile file);


    //文件删除
    @RequestMapping(value="api/vedio/fileDelete",method = RequestMethod.DELETE)
    public Integer fileDelete(@RequestParam("path") String path);

}
