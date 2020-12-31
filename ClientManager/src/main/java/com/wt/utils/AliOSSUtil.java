package com.wt.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName AliOSSUtil
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/11/19 13:31
 **/
public class AliOSSUtil {
    public static String ossUpload(File file){
        String bucketDomian="https://image-un.oss-cn-zhangjiakou.aliyuncs.com";
        String endpoint="https://oss-cn-zhangjiakou.aliyuncs.com";
        String accessKeyId="LTAI4GAVLwLkPD7pvz5JhPZV";
        String accessKeySecret="e8ZEEjBy0llxzoYFfSApCjJnF4G6jj";
        String bucketName="image-un";
        String fileDir="image/qzw";
        String fileName=file.getName();
        String fileKey= UUID.randomUUID().toString()+fileName.substring(fileName.indexOf("."));
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileDir+fileKey,file);
        ossClient.shutdown();
        return bucketDomian+"/"+fileDir+fileKey;
    }

    public static void main(String[] args) {
        File file=new File("C:/Users/UnKnW/Pictures/addBg.png");
        String url=ossUpload(file);
        System.out.println(url);
    }
}
