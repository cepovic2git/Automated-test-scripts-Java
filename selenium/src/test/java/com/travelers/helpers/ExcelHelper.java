package com.travelers.helpers;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExcelHelper {

    public static Object[][] readExcelFile(File file) throws IOException {

        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumbers = sheet.getLastRowNum();
        int colNumbers = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowNumbers][colNumbers];

        for(int i=0;i<rowNumbers;i++) {
            Row row = sheet.getRow(i+1);
            for(int j=0;j<row.getLastCellNum();j++) {
                data[i][j] = row.getCell(j).getStringCellValue();
            }
        }

        return data;

    }

    public static void main(String[] args) throws IOException {
        Object[][] data = readExcelFile(new File("src//main//resources//files//Dane.xlsx"));
        System.out.println(data[0][0]);
        System.out.println(data[0][1]);
    }

}
