/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsinghuadtv.www.entity;

/**
 *
 * @author liwenbo
 */
public class UserInfo
{
    public String usernumber;
    public String username;
    public String password;
    public String telephone;
    public String email;
    public String school;
    public String area;
    public String grade;
    public String classes;
    public String realname;
    public String idcradno;
    public String salt;
    public String version;
    public String createTime;
    public String type;

    @Override
    public String toString()
    {
        return "UserInfo{" + "usernumber=" + usernumber + ", username=" + username + ", password=" + password + ", telephone=" + telephone + ", email=" + email + ", school=" + school + ", area=" + area + ", grade=" + grade + ", classes=" + classes + ", realname=" + realname + ", idcradno=" + idcradno + ", salt=" + salt + ", version=" + version + ", createTime=" + createTime + ", type=" + type + '}';
    }

   
    
    
   
}
