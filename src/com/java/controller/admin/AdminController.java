package com.java.controller.admin;

import com.java.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description:
 * author: ws
 * time: 2020/3/4 22:41
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/test1")
    public @ResponseBody List<String> test1(){
        return Arrays.asList("中国","美国","日本" );
    }

    /**
     * 转发跳转页面
     * @return
     */
    @RequestMapping("/toDemo1")
    public String toDemo1(){
        return "demo1";
    }

    /**
     * 转发跳转页面
     * @return
     */
    @RequestMapping("/toDemo2")
    public String toDemo2(){
        return "demo2";
    }

    /**
     * 单个文件上传
     * @param bigHeadImg
     * @return
     */
    @RequestMapping("uploadFile")
    public @ResponseBody boolean uploadFile(MultipartFile bigHeadImg){
        try {
            //获取上传文件的原始名
            String originalFilename = bigHeadImg.getOriginalFilename();
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
            bigHeadImg.transferTo(new File(filePath + "/"  + uuid + ext));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 多个文件的上传
     * @param request
     * @return
     */
    @RequestMapping("uploadManyFiles")
    public @ResponseBody boolean uploadManyFiles(MultipartRequest request){
//        return FileUploadUtil.uploadFiles(request,"wenjianS");
        List<MultipartFile> fileList = request.getFiles("wenjianS");
        //遍历List集合,判断下fileList不能为null，否则报空指针异常
        try {
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
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }


//    /**
//     * 多个文件的上传的方法二：通过封装好的 类直接用，和方法一其实一样，只不过 简化封装了
//     * @param request
//     * @return
//     */
//    @RequestMapping("uploadManyFiles")
//    public @ResponseBody Map<String, Object> uploadManyFiles(MultipartRequest request){
//        return FileUploadUtil.uploadFiles(request,"wenjianS");
//    }

}
