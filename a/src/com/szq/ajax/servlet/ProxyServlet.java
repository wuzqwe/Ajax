package com.szq.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/proxy")
public class ProxyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过httpclient组件，发送Http GET请求，访问TargetServlet
        // 目标地址
//        String url = "https://www.baidu.com";
        String url = "http://localhost:8081/b/target";
        HttpGet httpGet = new HttpGet(url);

        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        System.out.println("调用URL: " + httpGet.getURI());

        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        HttpResponse resp = httpClient.execute(httpGet);
        //        System.out.println("Response toString()" + response.toString());
        HttpEntity entity = resp.getEntity();
        System.out.println("返回状态码：" + resp.getStatusLine());


        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
//        System.out.println("返回消息：" + responseSB);
        reader.close();

//        System.out.println("我是");
        httpClient.close();

        //b站点响应回来的数据
        response.getWriter().print(responseSB);
    }
}
