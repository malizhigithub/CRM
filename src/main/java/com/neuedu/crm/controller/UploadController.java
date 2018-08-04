package com.neuedu.crm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    
    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    @Operation(name="上传图片")
    @RequiresAuthentication
    @RequestMapping("/images")
    @ResponseBody
    public Map<String, Object> uploadImages(@RequestParam("images") MultipartFile[] images, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> imagePaths = new ArrayList<String>();
        //用来标记上传文件中有没有失败的
        boolean flag = true;
        
        //上传文件路径
        String path = request.getServletContext().getRealPath("/upload/");
        String uri =  request.getContextPath() 
                    + "/upload/";
        //images数组不能为空且长度大于0
        if(images != null && images.length > 0) {
            for (MultipartFile multipartFile : images) {
                //保存文件，并获得文件路径
                String imagePath = saveImage(multipartFile, path, uri);
                if (imagePath != null) {
                    //把图片的路径存储起来
                    imagePaths.add(imagePath);
                }else {
                    //有图片上传失败
                    flag = false;
                }
            }
        }
        if(flag) {
            map.put("errno", 0);
        }else {
            map.put("errno", -1);
        }
        map.put("data", imagePaths);
        return map;
    }
    
    /**
     * 
     * 描述：保存文件
     * @author wanghaoyu
     * @version 1.0
     * @param file
     * @param path
     * @return String 文件存储的路径
     * @exception Nothing
     * @since 1.8
     *
     */
    public String saveImage(MultipartFile file, String path ,String uri) {
        //判断文件是否为空
        if(!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if(!filepath.exists()) {
                    filepath.mkdirs();
                }
                //获取原始的图片名
                String filename = file.getOriginalFilename();
                //获取文件后缀
                String extension = filename.substring(filename.lastIndexOf("."));
                //重新为图片命名
                String rename = UUID.randomUUID().toString() + extension;
                
                String savePath = path + rename;
                String imageURI = uri + rename;
                file.transferTo(new File(savePath));
                
                logger.info("上传了一个文件，路径为："+ path+rename);
                logger.info("线上地址为：" + imageURI);
                
                return imageURI;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    
}
