package com.szq.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.szq.ajax.pojo.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ajaxrequest6")
public class Ajax6RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        /*StringBuilder jsonStr=new StringBuilder();
        jsonStr.append();*/
//        String jsonStr="[{\"name\":\"王五\",\"age\":\"20\",\"addr\":\"北京\"},{\"name\":\"李四\",\"age\":\"22\",\"addr\":\"湖南\"}]";

//        out.print(jsonStr);
//        StringBuilder json = new StringBuilder();
        String jsonStr1="";

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String username = request.getParameter("username");
        List<Student> list=new ArrayList<>();
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc", "root", "137152001szq");
            //获取预编译对象
            String sql="select id,name,age,addr from t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                String addr = rs.getString("addr");
                Student student = new Student(name, age, addr);
                list.add(student);
            }
            jsonStr1 = JSON.toJSONString(list);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        out.print(jsonStr1);
    }
}
