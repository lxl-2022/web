package com.ill.web.utils;

import com.aliyun.oss.*;
import com.ill.web.pojo.Imgfile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    private String accessKeyId = "123";
    private String accessSecret = "123";
    private String bucketName = "web-pp1223";

    public Imgfile upload(MultipartFile file,String uuid) throws IOException {
        //获取文件的输入流
        InputStream inputStream = file.getInputStream();

        //避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String filename = uuid+ originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessSecret);
        ossClient.putObject(bucketName,filename,inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+filename;

        Imgfile imgf = new Imgfile(url,filename.substring(filename.lastIndexOf("_")));

        return imgf;


    }

    public void delete(String url) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessSecret);

        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            String objectName = url.split("/")[1];
            ossClient.deleteObject(bucketName, objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
