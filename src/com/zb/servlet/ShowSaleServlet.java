package com.zb.servlet;

import com.google.gson.Gson;
import com.zb.dao.DoorDao;
import com.zb.pojo.Door;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Rwenjie
 * @ProjectName: yhproject
 * @Description:
 * @Date: 2020/7/13   21:47
 **/
@WebServlet("/showsale")
public class ShowSaleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ShowSaleServlet");
        DoorDao doorDao = new DoorDao();
        List<Door> list = doorDao.showSale();
        System.out.println(list);
        //利用gson将对象转换成json格式的对象
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);
        response.getWriter().write(json);
    }
}
