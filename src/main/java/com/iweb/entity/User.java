package com.iweb.entity;

import lombok.Data;

/**
 * @author 陈郅治
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;


    //生成匿名名称的方法
    // llxisjerk
    // l*******k

    public String getAnonymousName(){
        if(name==null){
            return null;
        }
        if(name.length()<=1){
            return "*";
        }
        if(name.length()==2){
            return name.substring(0,1)+"*";
        }
        char[] cs = name.toCharArray();
            for (int i = 1 ; i < cs.length-1 ; i++) {
                cs[i]='*';
            }
            return new String(cs);
    }

}
