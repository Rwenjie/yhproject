package com.zb.servlet;

import com.zb.dao.DoorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doorDelete")
public class DoorDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DoorDao doorDao = new DoorDao();
        doorDao.deleteById(id);
        response.sendRedirect(""+request.getContextPath()+"/doorList");
    }
}