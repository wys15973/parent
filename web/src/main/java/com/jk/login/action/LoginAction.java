package com.jk.login.action;


import com.alibaba.fastjson.JSON;
import com.jk.login.service.LoginService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


@ParentPackage("struts-default")
@Namespace("/")
@Action(value = "login",results = {
        @Result(name = "login",location = "/login.jsp")
})
public class LoginAction {

    @Autowired
    private LoginService loginService;

    private List list;


    public String login(){
        return "login";
    }

    public void getData(){
        list = loginService.getData();
        for (int i = 0 ; i <list.size();i++){
            System.out.println(list.get(i).toString());
        }
        list.remove(list.size()-1);
        String json =  JSON.toJSONString(list);
        System.out.println(json);
    }


    public void findData(){
        list = loginService.findData();

        for(int i =0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }

       String jsonStr =  JSON.toJSONString(list);

        try {
            ServletActionContext.getResponse().setCharacterEncoding("gbk");
            ServletActionContext.getResponse().getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
