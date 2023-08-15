package com.szq.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        //创建一个User类型的对象
        User user = new User(1001, "zhangsan", 20);

        //将以上的User对象转化为json格式的字符串
        //使用阿里巴巴的fastjson组件中的JSON即可
        //fastjson中又一个类，叫做JSON 全部大写
        //JSON类中的方法都是静态方法，直接调用即可
        String jsonStr= JSON.toJSONString(user);
        System.out.println(jsonStr);


        List<User> userList=new ArrayList<>();
        User user1=new User(120,"list",30);
        User user2=new User(130,"wnadek",40);
        userList.add(user1);
        userList.add(user2);

        String jsonStr2=JSON.toJSONString(userList);
        System.out.println(jsonStr2);

    }
}
