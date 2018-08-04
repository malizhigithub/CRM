package com.neuedu.crm.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author huangwanzong
 * @date 2018/07/12
 */

@Operation(name="文件管理接口")
@Controller
@RequestMapping("file")
public class FileController {
    
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    
    
    /**
     * 描述：文件上传功能
     * 返回参数说明：
     *      status ： 标记操作是否成功，true 成功， false 失败
     *      code ： 标记操作代码 0 成功  -1 file不存在 -2 -3 保存文件时读写异常
     *      msg ： 返回的信息
     *      filename ： 当文件上传完成后，服务器保存的文件名
     *      
     * @author huangwanzong
     * @version 1.0
     * @param request
     * @param file 要上传的文件
     * @return Map
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="上传客户相关文件")
    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> fileUpload(HttpServletRequest request,MultipartFile file){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        if(file == null || file.isEmpty()) {
            map.put("status", false);
            map.put("code", -1);
            map.put("msg","上传文件为空");
            return map;
        }
        

        //上传文件路径
        String path = request.getServletContext().getRealPath("/upload/");
        //获取文件名
        String filename = file.getOriginalFilename();
        //获取文件后缀名，即获取文件类型
        String fileExt = filename.substring(filename.lastIndexOf("."));
        
        //随机生成一个uuid当做新的文件名
        String uuid = UUID.randomUUID().toString();
        String newFile = uuid + fileExt;
        File filepath = new File(path,newFile);
        
        //System.out.println(filepath.getAbsolutePath());
        logger.info("上传了一个文件：" + filepath.getAbsolutePath());
        
        
        //判断文件路径是否存在
        if(!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        
        //将文件保存到目标文件中
        try {
            file.transferTo(new File(path + File.separator + newFile));
        } catch (IllegalStateException e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("code", -2);
            map.put("msg","保存文件发送错误");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("code", -3);
            map.put("msg","文件读写错误");
            return map;
        }
        
        map.put("status", true);
        map.put("code", 0);
        map.put("msg","上传成功");
        map.put("filename", newFile);

        return map;
    }
}
