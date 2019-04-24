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
        String temp= filePath.replace(basic_path+ File.separator,"");
        this.filePath=java.net.URLEncoder.encode(temp,"utf-8");
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
}
