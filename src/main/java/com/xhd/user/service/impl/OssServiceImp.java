package com.xhd.user.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;

import com.xhd.user.service.OssService;
import com.xhd.user.util.PropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImp implements OssService {

    @Override
    public String upfile(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = PropertiesUtil.END_POINT;
        String accessKeyId = PropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = PropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = PropertiesUtil.BUCKET_NAME;

         String uploadUrl = null;

        try {
             //判断oss实例是否存在：如果不存在则创建，如果存在则获取
             OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
             if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
               ossClient.createBucket(bucketName);
                 //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
                 }

             //获取上传文件流
             InputStream inputStream = file.getInputStream();

             //构建日期路径：avatar/2019/02/26/文件名
             String filePath = new DateTime().toString("yyyy/MM/dd");

//            //文件名：uuid.扩展名
            String filename=file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
//            String fileType = original.substring(original.lastIndexOf("."));
             filename = uuid + filename;
//            String fileUrl = fileHost + "/" + filePath + "/" + newName;
             //文件上传至阿里云
            String dataP = new DateTime().toString("yyyy-MM-dd");

             filename=dataP+"/"+filename;
            ossClient.putObject(bucketName, filename, inputStream);

             // 关闭OSSClient。
             ossClient.shutdown();

             //获取url地址
//             uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
            String url="http://"+ bucketName + "." + endPoint + "/"+filename;
            return url;

            } catch (IOException e) {
//             throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
                e.printStackTrace();
            return null;
        }
    }
}
