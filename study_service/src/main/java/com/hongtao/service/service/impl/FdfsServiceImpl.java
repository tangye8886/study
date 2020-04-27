package com.hongtao.service.service.impl;

import com.hongtao.service.service.FdfsService;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Service
public class FdfsServiceImpl implements FdfsService{

    public static final String CONF_FILENAME="fdfs_client.conf";
    public static final String SERVER_PATH="http://192.168.196.148:80/";

    @Override
    public String fileUpload(MultipartFile file) {
        String path="";
        try {
            ClientGlobal.init(CONF_FILENAME);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageServer storageServer=null;
            StorageClient storageClient=new StorageClient(trackerServer,storageServer);
            String ext_Name = file.getOriginalFilename().split("\\.")[1];

            String fileIds[]=storageClient.upload_file(file.getBytes(), ext_Name, null);
            byte[] bytes = null;
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("组名："+fileIds[0]);
            System.out.println("路径:"+fileIds[1]);
            path=SERVER_PATH+fileIds[0]+"/"+fileIds[1];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return path;
    }

    @Override
    public Integer deleteFile(String filePath) {
        Integer result=0;
        try {
            ClientGlobal.init(CONF_FILENAME);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);

            String s=filePath.substring(filePath.indexOf("group"));
            String group=s.substring(0,s.indexOf("/"));
            String path=s.substring(s.indexOf("/")+1,s.length());

            System.out.println(group);
            System.out.println(path);

            int i = storageClient.delete_file(group, path);
            if(i==0)
            {
                result=1;
            }else{
                result=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }
}
