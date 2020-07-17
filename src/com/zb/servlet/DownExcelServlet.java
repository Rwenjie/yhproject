package com.zb.servlet;

import com.zb.utils.ExcelPOI;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Rwenjie
 * @ProjectName: yhproject
 * @Description:
 * @Date: 2020/7/14   17:01
 **/
@WebServlet("/downexcel")
public class DownExcelServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建一个流，将我们生成的excel对象返回给浏览器
        ServletOutputStream out = response.getOutputStream();
        //2.设置头文件，让浏览器去下载
        response.setHeader("Content-Disposition","attachment;fileName=doorlist.xls");
        //3.预先设置表头
        String [] titles = {"编号","店铺名称","联系方式","地址","销售额"};
        //调用ExcelPOI（我们自己创建的类）里面的方法，帮助我们生成excel
        ExcelPOI excelPOI = new ExcelPOI();
        excelPOI.export(titles,out);
    }
}
