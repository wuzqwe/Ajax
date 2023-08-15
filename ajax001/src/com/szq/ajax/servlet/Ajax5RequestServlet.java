package com.szq.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/ajaxrequest5")
public class Ajax5RequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean success=false;
        String username = request.getParameter("username");
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc", "root", "137152001szq");
            //获取预编译对象
            String sql="select id,name from t_user where name=?";
             ps = conn.prepareStatement(sql);
             ps.setString(1,username);
            rs = ps.executeQuery();
            if (rs.next()){
                success=true;
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
        if (success){
            out.print("<font color='red'>用户名以存在</font>");
        }else {
            out.print("<font color='green'>用户名可用使用</font>");
        }
    }
}
