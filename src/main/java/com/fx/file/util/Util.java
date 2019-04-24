package com.fx.file.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    //正则匹配
    public static boolean  match(String s,String regEx)
    {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }


    //文件路径转url编码
    public static String urlEncode(String path) throws UnsupportedEncodingException {
        String temp=path.replace(File.separator,"/");
        String names[]=temp.split("/");
        StringBuffer buffer=new StringBuffer();
        if(Util.match(temp,".+(.jpg|.JPG|.gif|.GIF|.mp4|.txt)$"))
        {
            buffer.append("static/");
        }
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
