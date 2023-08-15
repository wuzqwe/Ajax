package com.szq.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.szq.ajax.pojo.Area;
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

@WebServlet("/listArea")
public class ListAreaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Area> list=new ArrayList<>();
        String pcode = request.getParameter("pcode");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc","root","137152001szq");
            if (pcode==null){
                String sql="select code,name from t_area where pcode is null";
                ps = conn.prepareStatement(sql);
            }else {
                String sql="select code,name from t_area where pcode=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,pcode);
            }
            rs = ps.executeQuery();
            while (rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                Area area = new Area(code,name);
                list.add(area);
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
            if (ps != null) {
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
        String jsonString = JSON.toJSONString(list);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(jsonString);
    }
}
