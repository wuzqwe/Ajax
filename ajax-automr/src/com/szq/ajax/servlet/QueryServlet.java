package com.szq.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.szq.ajax.pojo.City;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        //jdbc代码连接数据库
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<City> cities=new ArrayList<>();
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc", "root", "137152001szq");
            //获取预编译对象
            String sql="select content from t_ajax where content like ?";//模糊查询的时候，条件不建议使用%开始，因为会让字段上的索引失效，查询效率降低
            ps = conn.prepareStatement(sql);
            ps.setString(1,keyword+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                String content = rs.getString("content");
                City city = new City(content);
                cities.add(city);
            }
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
        String jso = JSON.toJSONString(cities);
        response.getWriter().print(jso);
    }
}
