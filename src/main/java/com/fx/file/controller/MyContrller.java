package com.fx.file.controller;


import com.fx.file.domain.MyFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyContrller {

    @Value("${localPath}")
    private String basic_path;


    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) throws UnsupportedEncodingException {

        String basic_url=request.getRequestURL().toString().replace("http://","").split("/")[0];

        File file=new File(basic_path);
        File[] files = file.listFiles();
        List<MyFile> fileList = new ArrayList<>();
        if(files!=null)
        {
            for (File f : files) {
                MyFile myFile=new MyFile(basic_path,basic_url,f.getName(),f.getAbsolutePath());
                fileList.add(myFile);
            }
        }
        return new ModelAndView("index","list",fileList);
    }


    @GetMapping("/**/{suffix:^(?:(?!.+(?:.jpg|.JPG|.gif|.GIF|.mp4|.txt)$).)*$}")
    public ModelAndView intoFolder(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取当前相对url地址
        String url= request.getRequestURI();
        //转换
        url=java.net.URLDecoder.decode(new String(url.getBytes("iso-8859-1"),"utf-8"), "UTF-8");
        //组合出文件绝对路径
        String path=basic_path+url;
        String basic_url=request.getRequestURL().toString().replace("http://","").split("/")[0];
        File file=new File(path);
        File[] files = file.listFiles();
        List<MyFile> fileList = new ArrayList<>();
        if(files!=null)
        {
            for (File f : files) {
                MyFile myFile=new MyFile(basic_path,basic_url,f.getName(),f.getAbsolutePath());
                fileList.add(myFile);
            }
        }
        return new ModelAndView("index","list",fileList);
    }
}
