package com.zb.servlet;

import com.zb.dao.DoorDao;
import com.zb.pojo.Door;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doorList")
public class DoorListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("门店列表查询");
        DoorDao doorDao = new DoorDao();
        try {
            List<Door> list = doorDao.findAll();
            request.setAttribute("list",list);
            request.getRequestDispatcher("/door_list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
