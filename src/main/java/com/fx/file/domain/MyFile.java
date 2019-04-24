package com.fx.file.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class MyFile {
    private String basic_path;
    private String fileName;
    private String filePath; //相对路径并且以url编码存储

    public MyFile(String basic_path, String fileName, String filePath) throws UnsupportedEncodingException {
        this.basic_path = basic_path;
        this.fileName = fileName;
        //得出相对路径
        String temp= filePath.replace(basic_path+ File.separator,"");
        //转换成url格式
        this.filePath=urlEncode(temp);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) throws UnsupportedEncodingException {
        String temp= filePath.replace(basic_path+ File.separator,"");
        this.filePath=java.net.URLEncoder.encode(temp,"utf-8");
    }

    //文件路径转url编码
    private String urlEncode(String path) throws UnsupportedEncodingException {
        String temp=path.replace(File.separator,"/");
        String names[]=temp.split("/");
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<names.length;i++)
        {
            //replace 掉+，因为空格会翻译成+，不符合需求
            String s=java.net.URLEncoder.encode(names[i],"utf-8").replace("+","%20");
            if(i!=names.length-1)
                buffer.append(s+"/");
            else
                buffer.append(s);
        }
        return buffer.toString();
    }





}
