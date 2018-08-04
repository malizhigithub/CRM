package com.neuedu.crm.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuedu.crm.utils.Operation;

/**
 * 验证码获取
 * @author huangqingwen
 *
 */
@Operation(name="验证码控制器")
@Controller
@RequestMapping("/verify")
public class VerifyCodeController{
	
	@Operation(name="获取验证码")
	@RequestMapping("/getVerifyCode")
    public Map<String, Object> getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
       
        int width=60;
        int height=20;
        
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        //创建内存图像并获得图形上下文
        BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        
        /*
         * 产生随机验证码
         * 定义验证码的字符表
         */
        String chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands=new char[4];
        int length = 4;
        for(int i=0;i<length;i++){
            //int rand=(int) (Math.random() *36);
            int rand = new Random().nextInt(36);
            rands[i]=chars.charAt(rand);
        }
        
        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        
        /*
         * 随机产生120个干扰点
         */
        int len = 120;
        for(int i=0;i< len ;i++){
            int x=(int)(Math.random()*width);
            int y=(int)(Math.random()*height);
            //int red=(int)(Math.random()*255);
            int red = new Random().nextInt(255);
            //int green=(int)(Math.random()*255);
            int green = new Random().nextInt(255);
            //int blue=(int)(Math.random()*255);
            int blue = new Random().nextInt(255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));
        
        //在不同高度输出验证码的不同字符
        g.drawString(""+rands[0], 1, 17);
        g.drawString(""+rands[1], 16, 15);
        g.drawString(""+rands[2], 31, 18);
        g.drawString(""+rands[3], 46, 16);
        g.dispose();
        
        //将图像传到客户端
        try {
			ServletOutputStream sos=response.getOutputStream();
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(image, "JPEG", baos);
			byte[] buffer=baos.toByteArray();
			response.setContentLength(buffer.length);
			sos.write(buffer);
			baos.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        request.getSession().setAttribute("verifyCode", new String(rands));
        return null;
    }
}
