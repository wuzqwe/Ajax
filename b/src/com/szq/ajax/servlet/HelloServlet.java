package com.szq.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");// 允许某个
        response.setContentType("text/html");
//        System.out.println("sdisfk");
        response.getWriter().print("你好大手笔");
        System.out.println("你好打算");
//        response.getWriter().print("{\"username\":\"zhangsan\"}");
        //设置响应头，允许ajax跨域请求
//        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有*/
    }
}
