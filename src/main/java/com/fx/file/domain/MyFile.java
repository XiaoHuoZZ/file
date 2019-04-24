package com.fx.file.domain;

import com.fx.file.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class MyFile {
    private String basic_path;
    private String fileName;
    private String urlPath; //相对路径并且以url编码存储
    private String basic_url;

    public MyFile(String basic_path, String basic_url,String fileName, String filePath) throws UnsupportedEncodingException {
        this.basic_path = basic_path;
        this.basic_url=basic_url;
        this.fileName = fileName;
        //得出文件相对路径
        String temp= filePath.replace(basic_path,"");
        //转换成url格式
        this.urlPath=Util.urlEncode(temp);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //返回绝对url路径
    public String getUrlPath() {
        return "http://"+basic_url+"/"+urlPath;
    }

    public void setFilePath(String filePath) throws UnsupportedEncodingException {
        String temp= filePath.replace(basic_path+ File.separator,"");
        this.urlPath=java.net.URLEncoder.encode(temp,"utf-8");
    }







}
