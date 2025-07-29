package com.focp.dto;

import com.focp.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    // Method to check the input file is Excel or not
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }else{
            return false;
        }
    }

    // Method to convert excel to list of products
    public static List<Product> convertExcelToList(InputStream is){
        List<Product> arr = new ArrayList<>();
        try{

           XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;

            Iterator<Row> iterator = sheet.iterator();

            while(iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber== 0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                Product p = new Product();
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid)
                    {
                        case 0:
                            p.setProductId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setProductPrice(cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                arr.add(p);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

}
