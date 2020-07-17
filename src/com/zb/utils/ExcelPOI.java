package com.zb.utils;

import com.zb.dao.DoorDao;
import com.zb.pojo.Door;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @Author: Rwenjie
 * @ProjectName: yhproject
 * @Description:
 * @Date: 2020/7/14   17:10
 **/
public class ExcelPOI {

    /*
     * 利用此方法生成Excel
     */

    public void export(String[] titles, ServletOutputStream out){
        //以下代码能百度复制就不用了去记，懂逻辑，懂值的替换就行
        try {
            //以下代码，能百度复制就不要去记，懂逻辑，懂值的替换即可
            //1.创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.在workbook中添加一个sheet，对应Excel中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            //3.在sheet中添加表头第0行
            HSSFRow row = hssfSheet.createRow(0);
            //4.创建单元格，并设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //4.1居中样式,这里只设置居中，其他样式可自行百度
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
            //4.2创建单元格
            HSSFCell hssfCell = null;
            for (int i=0;i<titles.length;i++){
                hssfCell = row.createCell(i);
                hssfCell.setCellValue(titles[i]);
                hssfCell.setCellStyle(hssfCellStyle);
            }

            DoorDao doorDao = new DoorDao();
            List<Door> list = doorDao.findAll();
            //创建行，注意行的下标从0开始，之前已经设置了0了
            for (int i=0;i<list.size();i++){
                row = hssfSheet.createRow(i+1);
                Door door = list.get(i);
                Integer id= null;
                if(door.getId()!=null){
                    id = door.getId();
                }
                row.createCell(0).setCellValue(id);
                String name ="";
                if (door.getName()!=null){
                    name = door.getName();
                }
                row.createCell(1).setCellValue(name);
                String tel = "";
                if(door.getTel()!=null){
                    tel=door.getTel();
                }
                row.createCell(2).setCellValue(tel);
                String addr = "";
                if(door.getAddr()!=null){
                    addr=door.getAddr();
                }
                row.createCell(3).setCellValue(addr);
                String sale = "";
                if (door.getSale()!=null){
                    sale=door.getSale();
                }
                row.createCell(4).setCellValue(sale);
                //设置单元格宽度自适应
                hssfSheet.autoSizeColumn((short)i);
            }
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
