package rozetka.autotest.support;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Custom {

    public static boolean writeToFile(List productList) {
        for (Object i:productList) {
            try {
                FileWriter writer = new FileWriter("ProductList.txt", true);
                writer.write(i.toString());
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean deleteFile() {
        File file = new File("ProductList.txt");
        if(file.delete()){
            System.out.println("ProductList.txt файл был удален с корневой папки проекта");
        }else System.out.println("ProductList.txt не был найден в корневой папке проекта");
        return true;
    }

    public static boolean sendEmail(String setFileName) {
        return SendMail.send(setFileName);
    }


    public static void writeToExcel(String[][] productsFirstSheet, String[][] productsSecondSheet) throws IOException {
        Workbook workbook = new SXSSFWorkbook();

        Sheet sheet1 = workbook.createSheet("Products");
        int productsLength = productsFirstSheet.length;
        for(int i = 0; i < productsLength; i++) {
            Row row = sheet1.createRow(i);
            for(int j = 0; j < 1; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(productsFirstSheet[i][j]);
                Cell cel2 = row.createCell(j+1);
                cel2.setCellValue(productsFirstSheet[i][j+1]);
            }
        }

        Sheet sheet2 = workbook.createSheet("MostPopular");
        int MostPopularProductsLength = productsSecondSheet.length;
        for(int i = 0; i < MostPopularProductsLength; i++) {
            Row row = sheet2.createRow(i);
            for(int j = 0; j < 1; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(productsSecondSheet[i][j]);
                Cell cel2 = row.createCell(j+1);
                cel2.setCellValue(productsSecondSheet[i][j+1]);
            }
        }

        // Voila!
        FileOutputStream out = new FileOutputStream("workbook.xlsx");
        workbook.write(out);
        out.close();
    }

    public static String[][] getArrayProducts(Object[] arrayProduct, boolean rangePrice, int min, int max){
        int chunk = 2;
        int step = 0;
        int productsNameAndPriceLength = arrayProduct.length / 2;
        String[][] resultProductsList = new String[productsNameAndPriceLength][2];
        for(int i=0;i<arrayProduct.length;i+=chunk){
            String productAndPrice = Arrays.toString(Arrays.copyOfRange(arrayProduct, i, Math.min(arrayProduct.length,i+chunk)));
            String priceOnly = productAndPrice.replace(" грн", "");
            String[] arrayProductAndPrice = priceOnly.split(", ");

            String price = arrayProductAndPrice[1].replace(" ", "");
            price = price.replace("]", "");
            if (rangePrice) {
                int result = Integer.parseInt(price);
                if (result > min && result < max) {
                    resultProductsList[step][0] = arrayProductAndPrice[0];
                    resultProductsList[step][1] = price;
                    step++;
                }
            }else{
                resultProductsList[step][0] = arrayProductAndPrice[0];
                resultProductsList[step][1] = price;
                step++;
            }
        }
        return resultProductsList;
    }

}
