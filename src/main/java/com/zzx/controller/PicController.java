package com.zzx.controller;

import com.zzx.dao.PictureMapper;
import com.zzx.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Created by Mr.John on 2018/10/27 10:29.
 **/

@RestController
public class PicController {

    @Autowired
    PictureMapper mapper;

    @PostMapping("/uploads")
    public void upload(HttpServletResponse response, @RequestParam(value = "file") MultipartFile file) throws ServletException, IOException {
        try {
// 文件保存路径

//String filePath = request.getSession().getServletContext().getRealPath("upload/");本地项目路径

            String filename = file.getOriginalFilename();//获取file图片名称
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            String mes = base64en.encode(messageDigest.digest(filename.getBytes("utf-8")));

            String filePath = "/home/upload/pic/"; //映射的地址
            uploadFile(file.getBytes(), filePath, filename);
//            /home/upload/pic/
            Picture picture = new Picture();
            String pa = "/home/upload/pic/"+"..." + mes;
            picture.setPath(pa);
            int sel = mapper.select(pa);
            if (sel == 0) {
                int isi = mapper.insert(picture.getPath());
                if (isi == 1) {
                    response.sendRedirect("success.html");
                    return;
                }
                response.sendRedirect("404.html");
                return;
            } else {
                response.sendRedirect("failuer.html");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("404.html");
        return;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
       /* @Select("SELECT count(id) FROM picture where path=#{Path}")
        int getByPath(@Param(value = "Path")String Path);*/
    }

}


