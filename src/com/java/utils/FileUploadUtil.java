package com.java.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description:文件上传工具类
 * author: ws
 * time: 2020/3/7 23:29
 */
public class FileUploadUtil {

    /**
     * 多文件上传
     * @param request：MultipartRequest类型
     * @param fieldName:取值为file控件的name属性值
     * @return
     */
    public static Map<String, Object> uploadFiles(MultipartRequest request, String fieldName){
        //定义一个Map集合封装结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//文件上传成功
        //遍历List集合,判断下fileList不能为null，否则报空指针异常
        try {
            //定义一个List集合，记录所有图片的上传相对地址
            List<String> hrefList = new ArrayList<>();

            List<MultipartFile> fileList = request.getFiles(fieldName);
            for( int i =0; fileList!=null && i < fileList.size(); i++){
                MultipartFile temp = fileList.get(i);

                //下面就和单文件上传流程一样，直接copy单文件上传
                //获取上传文件的原始名
                String originalFilename = temp.getOriginalFilename();
                System.out.println("originalFilename=" + originalFilename);
                //存放上传文件到G:\\uploads文件夹下
                //1.根据originalFilename玻璃出上传文件的后缀名
                String ext = originalFilename.substring(originalFilename.lastIndexOf("."));//从最后一个下标开始截取之后的字符串，得到如 .jpg .png
                System.out.println("ext="+ext);
                //2.动态的随机生成文件名：UUID     网卡号+时间戳
                String uuid = UUID.randomUUID().toString();
                System.out.println("uuid="+uuid);
                //3.动态的生成文件的目录结构G:\\uploads\\yyyyMMdd\\HH\\mm\\ss\\uuid+后缀名
                String afterPath = new SimpleDateFormat("yyyyMMdd\\HH\\mm\\ss").format(new Date());
                System.out.println("afterPath="+afterPath);
                //4.拼接文件上传的最终目录结构
                String filePath = "G:/uploads/"+afterPath;
                File file = new File(filePath);
                if(!file.exists()) {
                    file.mkdirs();
                }
                //5.核心文件上传的方法,即MultipartFile接口的核心方法
                temp.transferTo(new File(filePath + "/"  + uuid + ext));

                hrefList.add("uploads/"+afterPath + "/" + uuid + ext);
            }
            resultMap.put("hrefList", hrefList);
            return resultMap;
        } catch (IOException e) {
            resultMap.put("status","1");//文件上传失败
            return resultMap;
        }
    }
}
