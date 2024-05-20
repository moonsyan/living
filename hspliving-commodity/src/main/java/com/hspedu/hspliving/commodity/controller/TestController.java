package com.hspedu.hspliving.commodity.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.hspedu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-01 16:59
 */
@RestController
@Slf4j
public class TestController {

//    @RequestMapping("test")
//    public R TestUpload() throws ClientException {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        //EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//
//        String OSS_ACCESS_KEY_ID = "LTAI5tKDhJHwxkAomYKC5f39";
//        String OSS_ACCESS_KEY_SECRET = "LcR6G06F2FGmcSLlcWgA5RhGbrS7L0";
//
//               // 填写Bucket名称，例如examplebucket。
//        String bucketName = "hspliving-10001";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampl edir/exampleobject.txt。
//        String objectName = "blackUp.jpg";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        String filePath= "D:\\files\\pic\\headpic\\black.jpg";
//
//        // 创建OSSClient实例。
//        //OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
//        OSS ossClient = new OSSClientBuilder().build(endpoint, OSS_ACCESS_KEY_ID, OSS_ACCESS_KEY_SECRET);
//
//        try {
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
//            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//            // ObjectMetadata metadata = new ObjectMetadata();
//            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//            // metadata.setObjectAcl(CannedAccessControlList.Private);
//            // putObjectRequest.setMetadata(metadata);
//
//            // 上传文件。
//            PutObjectResult result = ossClient.putObject(putObjectRequest);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//        return null;
//    }


    @Resource
    private OSS ossClient;

    @RequestMapping("/test2")
    public R testUpload2() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\files\\pic\\headpic\\black.jpg");

        ossClient.putObject("hspliving-10001", "blackUp2.jpg", fileInputStream);

        ossClient.shutdown();
        return null;
    }




}
